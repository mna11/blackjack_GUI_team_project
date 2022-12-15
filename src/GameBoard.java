import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame{
	private final int cardWidth = 150;
	private final int cardHeight = 250;
	private final int gameBoardWidth = 475;
	private final int gameBoardHeight = 750;
	private final int scoreBoardHeight = 75;
	private final int chipBoardWidth = 225;
	private final int chipBoardHeight = 75;
	private final int battingChipBoardHeight = 400;
	
	private ImageIcon[] SPADES = new ImageIcon[13]; //SPADES[card.rank()-1] = card.rank()의 이미지 
	private ImageIcon[] HEARTS = new ImageIcon[13];
	private ImageIcon[] DIAMONDS = new ImageIcon[13];
	private ImageIcon[] CLUBS = new ImageIcon[13];
	private JLabel pScoreBoard = new JLabel();
	private JLabel cScoreBoard = new JLabel();
	private JLabel pChipBoard = new JLabel();
	private JLabel battingChipBoard = new JLabel();
	private JLabel[] pShowCard = new JLabel[3];
	private JLabel[] cShowCard = new JLabel[3];
	
	private MoreButton moreButton;
	private StopButton stopButton;
	private StartButton startButton;
	private EndButton endButton;
	private BattingUpButton battingUpButton;
	private BattingDownButton battingDownButton;
	
	private HumanPlayer humanPlayer;
	private ComputerPlayer computerPlayer;
	private Dealer dealer;
	private FileController fileController;
	
	private String playerName;
	private boolean isPlaying = false;
	private int battingChip;
	
	public GameBoard() {
		playerName = JOptionPane.showInputDialog("플레이어 이름을 입력하십시오.");
		humanPlayer = new HumanPlayer(11, playerName, this);
		computerPlayer = new ComputerPlayer(11);
		fileController = new FileController(humanPlayer);
		dealer = new Dealer();
		
		cardImgSetUp("SPADES", SPADES);
		cardImgSetUp("HEARTS", HEARTS);
		cardImgSetUp("DIAMONDS", DIAMONDS);
		cardImgSetUp("CLUBS", CLUBS);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		Container board = new Container();
		board.setLayout(new BorderLayout());
		Container southCon = new Container(); //southPanel안에 cScoreBoard와 cCardPanel이 들어감
		JPanel cCardPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		Container northCon = new Container(); //northPanel안에 pScoreBoard와 pCardPanel이 들어감
		JPanel pCardPanel = new JPanel();
		Container eastCon = new Container(); //eastPanel안에 pChipsBoard와 battingPanel, sAeButtonPanel이 들어감
		JPanel battingPanel = new JPanel();
		JPanel sAeButtonPanel = new JPanel();
		
		southCon.setLayout(new BorderLayout());
		
		//cScoreBoard 설정
		cScoreBoard.setFont(new Font("", Font.BOLD, 18));
		cScoreBoard.setForeground(Color.WHITE);
		cScoreBoard.setText("딜러 현 점수(첫 카드 미포함): " + computerPlayer.totalScore());
		cScoreBoard.setHorizontalTextPosition(cScoreBoard.CENTER);
		cScoreBoard.setIconTextGap(-gameBoardWidth);
		cScoreBoard.setIcon(imageIconImageSetSize(new ImageIcon("./card_image/SCORE_BACKGROUND.jpg")
				, gameBoardWidth, scoreBoardHeight));
		
		//cCardPanel 설정
		cCardPanel.setLayout(new GridLayout(1,3));
		for (int i = 0; i < 3; i++) {
			cShowCard[i] = new JLabel();
			cShowCard[i].setIcon(imageIconImageSetSize(new ImageIcon("./card_image/DEFAULT1.jpg"), 
					cardWidth, cardHeight));
			cCardPanel.add(cShowCard[i]);
		}
		
		//southPanel에 추가
		southCon.add(cScoreBoard, BorderLayout.NORTH);
		southCon.add(cCardPanel, BorderLayout.CENTER);
		
		//buttonPanel 설정 후 버튼 추가
		buttonPanel.setLayout(new GridLayout(1, 2));
		moreButton = new MoreButton("MORE", dealer, humanPlayer, this);
		stopButton = new StopButton("STOP", this);
		buttonPanel.add(moreButton);
		buttonPanel.add(stopButton);
		
		northCon.setLayout(new BorderLayout());
		
		//pScoreBoard 설정
		pScoreBoard.setFont(new Font("", Font.BOLD, 18));
		pScoreBoard.setForeground(Color.WHITE);
		pScoreBoard.setText("플레이어 현 점수: " + humanPlayer.totalScore());
		pScoreBoard.setHorizontalTextPosition(pScoreBoard.CENTER);
		pScoreBoard.setIconTextGap(-gameBoardWidth);
		pScoreBoard.setIcon(imageIconImageSetSize(new ImageIcon("./card_image/SCORE_BACKGROUND.jpg")
				, gameBoardWidth, scoreBoardHeight));
		
		//pCardPanel 설정
		pCardPanel.setLayout(new GridLayout(1,3));
		for (int i = 0; i < 3; i++) {
			pShowCard[i] = new JLabel();
			pShowCard[i].setIcon(imageIconImageSetSize(new ImageIcon("./card_image/DEFAULT2.jpg"), 
					cardWidth, cardHeight));
			pCardPanel.add(pShowCard[i]);
		}
		
		//northPanel에 추가
		northCon.add(pScoreBoard, BorderLayout.SOUTH);
		northCon.add(pCardPanel, BorderLayout.CENTER);
		
		eastCon.setLayout(new BorderLayout());
		
		//pChipBoard 설정
		pChipBoard.setFont(new Font("", Font.BOLD, 18));
		pChipBoard.setForeground(Color.WHITE);
		pChipBoard.setText("현재 보유한 칩:" + humanPlayer.getChip());
		pChipBoard.setHorizontalTextPosition(pChipBoard.CENTER);
		pChipBoard.setIconTextGap(-chipBoardWidth);
		pChipBoard.setIcon(imageIconImageSetSize(new ImageIcon("./card_image/BATTING_BACKGROUND.jpg")
				, chipBoardWidth, chipBoardHeight));
		
		//start 버튼과 end 버튼 패널 설정 및 버튼 설정
		sAeButtonPanel.setLayout(new GridLayout(1,2));
		startButton = new StartButton("START", this, battingChipBoard);
		endButton = new EndButton("END", this);
		startButton.setPreferredSize(new Dimension(chipBoardWidth/2, scoreBoardHeight));
		endButton.setPreferredSize(new Dimension(chipBoardWidth/2, scoreBoardHeight));
		sAeButtonPanel.add(startButton);
		sAeButtonPanel.add(endButton);
		
		battingPanel.setLayout(new BorderLayout());
		
		//battingChipBoard 설정
		battingChipBoard.setFont(new Font("", Font.BOLD, 30));
		battingChipBoard.setForeground(Color.WHITE);
		battingChipBoard.setText("0");
		battingChipBoard.setHorizontalTextPosition(battingChipBoard.CENTER);
		battingChipBoard.setIconTextGap(-chipBoardWidth);
		battingChipBoard.setIcon(imageIconImageSetSize(new ImageIcon("./card_image/BATTING_BACKGROUND.jpg")
				, chipBoardWidth, battingChipBoardHeight));
		
		//up버튼과 down버튼 설정
		battingUpButton = new BattingUpButton("UP", battingChipBoard, humanPlayer);
		battingDownButton = new BattingDownButton("DOWN", battingChipBoard);
		battingUpButton.setPreferredSize(new Dimension(chipBoardWidth/2, (gameBoardHeight - (scoreBoardHeight*2 + battingChipBoardHeight))/2));
		battingDownButton.setPreferredSize(new Dimension(chipBoardWidth/2, (gameBoardHeight - (scoreBoardHeight*2 + battingChipBoardHeight))/2));
		
		//battingPanel에 추가
		battingPanel.add(battingChipBoard, BorderLayout.CENTER);
		battingPanel.add(battingUpButton, BorderLayout.NORTH);
		battingPanel.add(battingDownButton, BorderLayout.SOUTH);
		
		//eastPanel에 추가
		eastCon.add(pChipBoard, BorderLayout.NORTH);
		eastCon.add(battingPanel, BorderLayout.CENTER);
		eastCon.add(sAeButtonPanel, BorderLayout.SOUTH);
		
		//board에 추가
		board.add(southCon, BorderLayout.NORTH);
		board.add(buttonPanel, BorderLayout.CENTER);
		board.add(northCon, BorderLayout.SOUTH);
		
		//cp에 추가
		cp.add(board, BorderLayout.CENTER);
		cp.add(eastCon, BorderLayout.EAST);
		
		//2장씩 나눠주고 시작
		//플레이어가 블랙잭인 경우 바로 gameOver()로 감
		init();
		
		setTitle("BLACKJACK_GUI");
		setSize(gameBoardWidth+chipBoardWidth, gameBoardHeight);
		
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
			cShowCard[i].setIcon(imageIconImageSetSize(new ImageIcon("./card_image/DEFAULT1.jpg"), 
					cardWidth, cardHeight));
		}
		for (int i = 0; i < 3; i++) {
			pShowCard[i].setIcon(imageIconImageSetSize(new ImageIcon("./card_image/DEFAULT2.jpg"), 
					cardWidth, cardHeight));
		}
		
		humanPlayer.cardSetup(11);
		computerPlayer.cardSetup(11);
		dealer.dealOneTo(humanPlayer);
		dealer.dealOneTo(computerPlayer);
		dealer.dealOneTo(humanPlayer);
		dealer.dealOneTo(computerPlayer);
		update();
		
	}
	
	/**
	 * 게임이 종료되었을 때, 승패 판정을 해준다.
	 */
	public void gameOver() {
		  cCardSetup(1);
	      while(computerPlayer.totalScore() <= 16) {
	    	 cCardSetup(2);
	      }
	      if (humanPlayer.totalScore() == 21 && humanPlayer.getCardCount() == 2) {
	    	  pScoreBoard.setText("블랙잭!");
	    	  humanPlayer.youWinBlackjack();
	    	  stopButton.setIsFirst(false);
	      }
	      else if(humanPlayer.totalScore() > 21) {
	         pScoreBoard.setText("플레이어 버스트: " + "(" + humanPlayer.totalScore() + ":" + computerPlayer.totalScore() + ")");
	         humanPlayer.youLose();
	         stopButton.setIsFirst(false);
	      }
	      else if(computerPlayer.totalScore() > 21) {
	    	  pScoreBoard.setText("딜러 버스트: " + "(" + humanPlayer.totalScore() + ":" + computerPlayer.totalScore() + ")");
	         humanPlayer.youWin();
	      }
	      else if(humanPlayer.totalScore() > computerPlayer.totalScore()) {
	    	  pScoreBoard.setText("플레이어 승리: " + "(" + humanPlayer.totalScore() + ":" + computerPlayer.totalScore() + ")");
	         humanPlayer.youWin();
	      }
	      else if(humanPlayer.totalScore() < computerPlayer.totalScore()) {
	    	 pScoreBoard.setText("딜러 승리: " + "(" + humanPlayer.totalScore() + ":" + computerPlayer.totalScore() + ")");
	         humanPlayer.youLose();
	      }
	      else if (humanPlayer.totalScore() == computerPlayer.totalScore()) {
	    	 pScoreBoard.setText("무승부: " + "(" + humanPlayer.totalScore() + ":" + computerPlayer.totalScore() + ")");
		     humanPlayer.youDraw();
	      }
	      cScoreBoard.setText("새 게임을 시작하시려면 STOP 버튼을 누르세요");
	      moreButton.setEnabled(false);
	      endButton.setEnabled(true);
	   }
	
	/**
	 * EndButton이 눌렸을 때, 호출되며 fileController의 EndSoWrite를 호출한다.
	 */
	public void pressEnd() {
		fileController.EndSoWrite();
		System.exit(1);
	}
	
	/**
	 * more버튼을 눌렀을 때 호출됨, 플레이어 card의 공개 상태를 변경한다.
	 */
	public void update() {
		Card[] hCD = humanPlayer.showCards();
		Card[] cCD = computerPlayer.showCards();
		int cnt = hCD.length == 2 ? 1 : 2; //처음 배부했을 때는 length가 2이므로 1로 해준다.
		
		if (cnt == 1) {
			ImageIcon img = cardMatchImg(cCD[1]);
			cShowCard[1].setIcon(img);
			cScoreBoard.setText("딜러 현 점수(첫 카드 미포함): " + computerPlayer.nFirstTotalScore());
			ImageIcon img2 = cardMatchImg(hCD[1]);
			pShowCard[1].setIcon(img2);
			pScoreBoard.setText("플레이어 현 점수(배팅 후 2장 공개): " + humanPlayer.nFirstTotalScore());
		}
		else {
			for (int i = hCD.length-1; cnt >= 0; i--) {
				ImageIcon img = cardMatchImg(hCD[i]);
				pShowCard[cnt--].setIcon(img);
			}
			pScoreBoard.setText("플레이어 현 점수: " + humanPlayer.totalScore());
		}
		if (scoreCheck() == 2)
			gameOver();
	}
	
	/**
	 * 플레이어 카드를 한장만 공개하게 한다.
	 */
	public void showFirstHC() {
		Card[] hCD = humanPlayer.showCards();
		ImageIcon img = cardMatchImg(hCD[0]);
		pShowCard[0].setIcon(img);
		pScoreBoard.setText("플레이어 현 점수: " + humanPlayer.totalScore());
	}
	
	/**
	 * 배팅칩을 플레이어 실제 칩에 적용한다.
	 * @param n
	 */
	public void setBattingChip(int n) {
		battingChip = n;
		int chips = humanPlayer.getChip();
		humanPlayer.setChip(chips - n);
		pChipBoard.setText("현재 보유한 칩:" + humanPlayer.getChip());
		battingChipBoard.setText(Integer.toString(n));
	}
	
	/**
	 * 배팅칩의 개수를 반환
	 * @return 배팅칩의 개수를 반환
	 */
	public int getBattingChip() {
		return battingChip;
	}
	
	/**
	 * isPlaying 값을 바꾸고 buttonEnableSet()을 실행함
	 * @param b isPlaying값을 세팅할 boolean값
	 */
	public void setButtonEnable(boolean b) {
		isPlaying = b;
		buttonEnableSet();
	}
	
	/**
	 * computer Card의 공개 상태를 바꾼다.
	 * @param n 보여주고 싶은 카드 개수 - 1;
	 */
	private void cCardSetup(int n) {
		int cnt = n;
		if (cnt != 1)
			dealer.dealOneTo(computerPlayer);
        
        Card[] cCD = computerPlayer.showCards();
        for (int i = cCD.length-1; cnt >= 0; i--) {
           ImageIcon img = cardMatchImg(cCD[i]);
           cShowCard[cnt--].setIcon(img);
        }
        cScoreBoard.setText("딜러 현 점수: " + computerPlayer.totalScore());
	}
	
	/**
	 * isPlaying에 따라 버튼이 활성화 상태가 변함
	 * more, stop은 게임 실행 중 사용하므로 isPlaying에 따라
	 * start, end, up, down은 배팅 시간에 사용하므로 !isPlaying
	 */
	private void buttonEnableSet() {
		moreButton.setEnabled(isPlaying);
		stopButton.setEnabled(isPlaying);
		startButton.setEnabled(!isPlaying);
		endButton.setEnabled(!isPlaying);
		battingUpButton.setEnabled(!isPlaying);
		battingDownButton.setEnabled(!isPlaying);
	}
	
	/** 
	 * 현재 플레이어의 상태(속행가능, 블랙잭, 버스트)를 판단해주는 메소드
	 * @return 속행가능: 0, 블랙잭: 1, 버스트: 2
	 */
	private int scoreCheck() {
		int hPScore = humanPlayer.totalScore();
		if (hPScore < 21) return 0;
		else if (hPScore == 21) return 1;
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
		if (s == "SPADES") img = SPADES[r-1];
		else if (s == "HEARTS") img = HEARTS[r-1];
		else if (s == "DIAMONDS") img = DIAMONDS[r-1];
		else if (s == "CLUBS") img = CLUBS[r-1];
		
		return img;
	}
	
	/**
	 * 무늬와 ImageIcon 배열을 받아서 배열에 사진을 넣어주는 메소드 
	 * @param cardSuits 카드의 무늬 
	 * @param card ImageIcon 배열 
	 */
	private void cardImgSetUp(String cardSuits, ImageIcon card[]) {
		for (int idx = 0; idx < 13; idx++) {
			String file = "./card_image/" + cardSuits+ "_" + Integer.toString(idx+1) + ".jpg";
			card[idx] = imageIconImageSetSize(new ImageIcon(file), cardWidth, cardHeight);
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
