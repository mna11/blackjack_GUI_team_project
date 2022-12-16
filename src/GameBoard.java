import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame{
	private final int card_width = 150;
	private final int card_height = 250;
	private final int game_board_width = 475;
	private final int game_board_height = 750;
	private final int score_board_height = 75;
	private final int chip_board_width = 225;
	private final int chip_board_height = 75;
	private final int battingchip_board_height = 400;
	
	private ImageIcon[] spades = new ImageIcon[13]; //spades[card.rank()-1] = card.rank()의 이미지 
	private ImageIcon[] hearts = new ImageIcon[13];
	private ImageIcon[] diamonds = new ImageIcon[13];
	private ImageIcon[] clubs = new ImageIcon[13];
	private JLabel p_score_board = new JLabel();
	private JLabel c_score_board = new JLabel();
	private JLabel p_chip_board = new JLabel();
	private JLabel battingchip_board = new JLabel();
	private JLabel[] p_show_card = new JLabel[3];
	private JLabel[] c_show_card = new JLabel[3];
	
	private MoreButton more_button;
	private StopButton stop_button;
	private StartButton startButton;
	private EndButton end_button;
	private BattingUpButton batting_up_button;
	private BattingDownButton batting_down_button;
	
	private HumanPlayer human_player;
	private ComputerPlayer computer_player;
	private Dealer dealer;
	private FileController file_controller;
	
	private String player_name;
	private boolean is_playing = false;
	private int batting_chip;
	
	public GameBoard() {
		player_name = JOptionPane.showInputDialog("플레이어 이름을 입력하십시오.");
		human_player = new HumanPlayer(11, player_name, this);
		computer_player = new ComputerPlayer(11);
		file_controller = new FileController(human_player);
		dealer = new Dealer();
		
		cardImgSetUp("spades", spades);
		cardImgSetUp("hearts", hearts);
		cardImgSetUp("diamonds", diamonds);
		cardImgSetUp("clubs", clubs);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		Container board = new Container();
		board.setLayout(new BorderLayout());
		Container south_con = new Container(); //south_con안에 c_score_board와 c_card_panel이 들어감
		JPanel c_card_panel = new JPanel();
		JPanel button_panel = new JPanel();
		Container north_con = new Container(); //north_con안에 p_score_board와 p_card_panel이 들어감
		JPanel p_card_panel = new JPanel();
		Container east_con = new Container(); //east_con안에 pChipsBoard와 batting_panel, s_and_e_button_panel이 들어감
		JPanel batting_panel = new JPanel();
		JPanel s_and_e_button_panel = new JPanel();
		
		south_con.setLayout(new BorderLayout());
		
		//c_score_board 설정
		c_score_board.setFont(new Font("", Font.BOLD, 18));
		c_score_board.setForeground(Color.WHITE);
		c_score_board.setText("딜러 현 점수(첫 카드 미포함): " + computer_player.totalScore());
		c_score_board.setHorizontalTextPosition(c_score_board.CENTER);
		c_score_board.setIconTextGap(-game_board_width);
		c_score_board.setIcon(imageIconImageSetSize(new ImageIcon("./card_image/SCORE_BACKGROUND.jpg")
				, game_board_width, score_board_height));
		
		//c_card_panel 설정
		c_card_panel.setLayout(new GridLayout(1,3));
		for (int i = 0; i < 3; i++) {
			c_show_card[i] = new JLabel();
			c_show_card[i].setIcon(imageIconImageSetSize(new ImageIcon("./card_image/DEFAULT1.jpg"), 
					card_width, card_height));
			c_card_panel.add(c_show_card[i]);
		}
		
		//south_con에 추가
		south_con.add(c_score_board, BorderLayout.NORTH);
		south_con.add(c_card_panel, BorderLayout.CENTER);
		
		//button_panel 설정 후 버튼 추가
		button_panel.setLayout(new GridLayout(1, 2));
		more_button = new MoreButton("MORE", dealer, human_player, this);
		stop_button = new StopButton("STOP", this);
		button_panel.add(more_button);
		button_panel.add(stop_button);
		
		north_con.setLayout(new BorderLayout());
		
		//p_score_board 설정
		p_score_board.setFont(new Font("", Font.BOLD, 18));
		p_score_board.setForeground(Color.WHITE);
		p_score_board.setText("플레이어 현 점수: " + human_player.totalScore());
		p_score_board.setHorizontalTextPosition(p_score_board.CENTER);
		p_score_board.setIconTextGap(-game_board_width);
		p_score_board.setIcon(imageIconImageSetSize(new ImageIcon("./card_image/SCORE_BACKGROUND.jpg")
				, game_board_width, score_board_height));
		
		//p_card_panel 설정
		p_card_panel.setLayout(new GridLayout(1,3));
		for (int i = 0; i < 3; i++) {
			p_show_card[i] = new JLabel();
			p_show_card[i].setIcon(imageIconImageSetSize(new ImageIcon("./card_image/DEFAULT2.jpg"), 
					card_width, card_height));
			p_card_panel.add(p_show_card[i]);
		}
		
		//north_con에 추가
		north_con.add(p_score_board, BorderLayout.SOUTH);
		north_con.add(p_card_panel, BorderLayout.CENTER);
		
		east_con.setLayout(new BorderLayout());
		
		//p_chip_board 설정
		p_chip_board.setFont(new Font("", Font.BOLD, 18));
		p_chip_board.setForeground(Color.WHITE);
		p_chip_board.setText("현재 보유한 칩:" + human_player.getChip());
		p_chip_board.setHorizontalTextPosition(p_chip_board.CENTER);
		p_chip_board.setIconTextGap(-chip_board_width);
		p_chip_board.setIcon(imageIconImageSetSize(new ImageIcon("./card_image/BATTING_BACKGROUND.jpg")
				, chip_board_width, chip_board_height));
		
		//start 버튼과 end 버튼 패널 설정 및 버튼 설정
		s_and_e_button_panel.setLayout(new GridLayout(1,2));
		startButton = new StartButton("START", this, battingchip_board);
		end_button = new EndButton("END", this);
		startButton.setPreferredSize(new Dimension(chip_board_width/2, score_board_height));
		end_button.setPreferredSize(new Dimension(chip_board_width/2, score_board_height));
		s_and_e_button_panel.add(startButton);
		s_and_e_button_panel.add(end_button);
		
		batting_panel.setLayout(new BorderLayout());
		
		//battingchip_board 설정
		battingchip_board.setFont(new Font("", Font.BOLD, 30));
		battingchip_board.setForeground(Color.WHITE);
		battingchip_board.setText("0");
		battingchip_board.setHorizontalTextPosition(battingchip_board.CENTER);
		battingchip_board.setIconTextGap(-chip_board_width);
		battingchip_board.setIcon(imageIconImageSetSize(new ImageIcon("./card_image/BATTING_BACKGROUND.jpg")
				, chip_board_width, battingchip_board_height));
		
		//up버튼과 down버튼 설정
		batting_up_button = new BattingUpButton("UP", battingchip_board, human_player);
		batting_down_button = new BattingDownButton("DOWN", battingchip_board);
		batting_up_button.setPreferredSize(new Dimension(chip_board_width/2, (game_board_height - (score_board_height*2 + battingchip_board_height))/2));
		batting_down_button.setPreferredSize(new Dimension(chip_board_width/2, (game_board_height - (score_board_height*2 + battingchip_board_height))/2));
		
		//batting_panel에 추가
		batting_panel.add(battingchip_board, BorderLayout.CENTER);
		batting_panel.add(batting_up_button, BorderLayout.NORTH);
		batting_panel.add(batting_down_button, BorderLayout.SOUTH);
		
		//east_con에 추가
		east_con.add(p_chip_board, BorderLayout.NORTH);
		east_con.add(batting_panel, BorderLayout.CENTER);
		east_con.add(s_and_e_button_panel, BorderLayout.SOUTH);
		
		//board에 추가
		board.add(south_con, BorderLayout.NORTH);
		board.add(button_panel, BorderLayout.CENTER);
		board.add(north_con, BorderLayout.SOUTH);
		
		//cp에 추가
		cp.add(board, BorderLayout.CENTER);
		cp.add(east_con, BorderLayout.EAST);
		
		//2장씩 나눠주고 시작
		init();
		
		setTitle("BLACKJACK_GUI");
		setSize(game_board_width+chip_board_width, game_board_height);
		
		buttonEnableSet();
		setVisible(true);
		setLocationRelativeTo(null); //게임 화면 스크린 정중앙 위치
		setResizable(false); //게임 화면 리사이즈 불가
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * 플레이 초기 설정을 해준다.
	 */
	public void init() {
		
		for (int i = 0; i < 3; i++) {
			c_show_card[i].setIcon(imageIconImageSetSize(new ImageIcon("./card_image/DEFAULT1.jpg"), 
					card_width, card_height));
		}
		for (int i = 0; i < 3; i++) {
			p_show_card[i].setIcon(imageIconImageSetSize(new ImageIcon("./card_image/DEFAULT2.jpg"), 
					card_width, card_height));
		}
		
		human_player.cardSetup(11);
		computer_player.cardSetup(11);
		dealer.dealOneTo(human_player);
		dealer.dealOneTo(computer_player);
		dealer.dealOneTo(human_player);
		dealer.dealOneTo(computer_player);
		update();
		
	}
	
	/**
	 * 게임이 종료되었을 때, 승패 판정을 해준다.
	 */
	public void gameOver() {
		  cCardSetup(1);
	      while(computer_player.totalScore() <= 16) {
	    	 cCardSetup(2);
	      }
	      if (human_player.totalScore() == 21 && human_player.getCardCount() == 2) {
	    	  p_score_board.setText("블랙잭!");
	    	  human_player.youWinBlackjack();
	    	  stop_button.setIsFirst(false);
	      }
	      else if(human_player.totalScore() > 21) {
	         p_score_board.setText("플레이어 버스트: " + "(" + human_player.totalScore() + ":" + computer_player.totalScore() + ")");
	         human_player.youLose();
	         stop_button.setIsFirst(false);
	      }
	      else if(computer_player.totalScore() > 21) {
	    	  p_score_board.setText("딜러 버스트: " + "(" + human_player.totalScore() + ":" + computer_player.totalScore() + ")");
	         human_player.youWin();
	      }
	      else if(human_player.totalScore() > computer_player.totalScore()) {
	    	  p_score_board.setText("플레이어 승리: " + "(" + human_player.totalScore() + ":" + computer_player.totalScore() + ")");
	         human_player.youWin();
	      }
	      else if(human_player.totalScore() < computer_player.totalScore()) {
	    	 p_score_board.setText("딜러 승리: " + "(" + human_player.totalScore() + ":" + computer_player.totalScore() + ")");
	         human_player.youLose();
	      }
	      else if (human_player.totalScore() == computer_player.totalScore()) {
	    	 p_score_board.setText("무승부: " + "(" + human_player.totalScore() + ":" + computer_player.totalScore() + ")");
		     human_player.youDraw();
	      }
	      c_score_board.setText("새 게임을 시작하시려면 STOP 버튼을 누르세요");
	      more_button.setEnabled(false);
	      end_button.setEnabled(true);
	   }
	
	/**
	 * EndButton이 눌렸을 때, 호출되며 file_controller의 EndSoWrite를 호출한다.
	 */
	public void pressEnd() {
		file_controller.EndSoWrite();
		System.exit(1);
	}
	
	/**
	 * more버튼을 눌렀을 때 호출됨, 플레이어 card의 공개 상태를 변경한다.
	 */
	public void update() {
		Card[] h_card_deck = human_player.showCards();
		Card[] c_card_deck = computer_player.showCards();
		int cnt = h_card_deck.length == 2 ? 1 : 2; //처음 배부했을 때는 length가 2이므로 1로 해준다.
		
		if (cnt == 1) {
			ImageIcon img = cardMatchImg(c_card_deck[1]);
			c_show_card[1].setIcon(img);
			c_score_board.setText("딜러 현 점수(첫 카드 미포함): " + computer_player.nFirstTotalScore());
			ImageIcon img2 = cardMatchImg(h_card_deck[1]);
			p_show_card[1].setIcon(img2);
			p_score_board.setText("플레이어 현 점수(배팅 후 2장 공개): " + human_player.nFirstTotalScore());
		}
		else {
			for (int i = h_card_deck.length-1; cnt >= 0; i--) {
				ImageIcon img = cardMatchImg(h_card_deck[i]);
				p_show_card[cnt--].setIcon(img);
			}
			p_score_board.setText("플레이어 현 점수: " + human_player.totalScore());
		}
		if (scoreCheck() == 2)
			gameOver();
	}
	
	/**
	 * 가려진 플레이어의 첫번째 카드를 공개하고 블랙잭 판단
	 */
	public void showFirstHC() {
		Card[] h_card_deck = human_player.showCards();
		ImageIcon img = cardMatchImg(h_card_deck[0]);
		p_show_card[0].setIcon(img);
		p_score_board.setText("플레이어 현 점수: " + human_player.totalScore());

		if(scoreCheck() == 1) gameOver();
	}
	
	/**
	 * 배팅칩을 플레이어 실제 칩에 적용한다.
	 * @param n
	 */
	public void setBattingChip(int n) {
		batting_chip = n;
		int chips = human_player.getChip();
		human_player.setChip(chips - n);
		p_chip_board.setText("현재 보유한 칩:" + human_player.getChip());
		battingchip_board.setText(Integer.toString(n));
	}
	
	/**
	 * 배팅칩의 개수를 반환
	 * @return 배팅칩의 개수를 반환
	 */
	public int getBattingChip() {
		return batting_chip;
	}
	
	/**
	 * is_playing 값을 바꾸고 buttonEnableSet()을 실행함
	 * @param b is_playing값을 세팅할 boolean값
	 */
	public void setButtonEnable(boolean b) {
		is_playing = b;
		buttonEnableSet();
	}
	
	/**
	 * computer Card의 공개 상태를 바꾼다.
	 * @param n 보여주고 싶은 카드 개수 - 1;
	 */
	private void cCardSetup(int n) {
		int cnt = n;
		if (cnt != 1)
			dealer.dealOneTo(computer_player);
        
        Card[] c_card_deck = computer_player.showCards();
        for (int i = c_card_deck.length-1; cnt >= 0; i--) {
           ImageIcon img = cardMatchImg(c_card_deck[i]);
           c_show_card[cnt--].setIcon(img);
        }
        c_score_board.setText("딜러 현 점수: " + computer_player.totalScore());
	}
	
	/**
	 * is_playing에 따라 버튼이 활성화 상태가 변함
	 * more, stop은 게임 실행 중 사용하므로 is_playing에 따라
	 * start, end, up, down은 배팅 시간에 사용하므로 !is_playing
	 */
	private void buttonEnableSet() {
		more_button.setEnabled(is_playing);
		stop_button.setEnabled(is_playing);
		startButton.setEnabled(!is_playing);
		end_button.setEnabled(!is_playing);
		batting_up_button.setEnabled(!is_playing);
		batting_down_button.setEnabled(!is_playing);
	}
	
	/** 
	 * 현재 플레이어의 상태(속행가능, 블랙잭, 버스트)를 판단해주는 메소드
	 * @return 속행가능: 0, 블랙잭: 1, 버스트: 2
	 */
	private int scoreCheck() {
		int h_player_score = human_player.totalScore();
		if (h_player_score < 21) return 0;
		else if (h_player_score == 21) return 1;
		else return 2; 
	}
	
	/**
	 * 카드 객체를 넣으면 알맞는 카드 이미지를 찾아 이미지 아이콘을 리턴해줌
	 * @param c 이미지를 찾고 싶은 카드 객체
	 * @return 카드 객체에 맞는 이미지 아이콘
	 */
	private ImageIcon cardMatchImg(Card c) {
		String s = c.getSuit();
		int r = c.getRank();
		
		ImageIcon img = new ImageIcon();
		if (s == "spades") img = spades[r-1];
		else if (s == "hearts") img = hearts[r-1];
		else if (s == "diamonds") img = diamonds[r-1];
		else if (s == "clubs") img = clubs[r-1];
		
		return img;
	}
	
	/**
	 * 무늬와 ImageIcon 배열을 받아서 배열에 사진을 넣어주는 메소드 
	 * @param card_suits 카드의 무늬 
	 * @param card ImageIcon 배열 
	 */
	private void cardImgSetUp(String card_suits, ImageIcon card[]) {
		for (int idx = 0; idx < 13; idx++) {
			String file = "./card_image/" + card_suits+ "_" + Integer.toString(idx+1) + ".jpg";
			card[idx] = imageIconImageSetSize(new ImageIcon(file), card_width, card_height);
		}
	}
	
	/**
	 * 이미지아이콘을 받아서 리사이즈 후 반환하는 메소드
	 * @param icon 리사이즈 하고싶은 이미지아이콘
	 * @param i 원하는 너비
	 * @param j 원하는 높이
	 * @return 리사이즈된 ImageIcon 반환
	 */
	private ImageIcon imageIconImageSetSize(ImageIcon icon, int i, int j) { 
		Image tmp1 = icon.getImage();  //ImageIcon을 Image로 변환.
		Image tmp2 = tmp1.getScaledInstance(i, j, Image.SCALE_SMOOTH);
		ImageIcon img = new ImageIcon(tmp2); 
		return img;
	}
}
