import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame{
	private final int cardWidth = 150;
	private final int cardHeight = 250;
	private final int gameBoardWidth = 475;
	private final int gameBoardHeight = 750;
	private final int scoreBoardHeight = 75;
	
	private ImageIcon[] SPADES = new ImageIcon[13]; //SPADES[card.rank()-1] = card.rank()의 이미지 
	private ImageIcon[] HEARTS = new ImageIcon[13];
	private ImageIcon[] DIAMONDS = new ImageIcon[13];
	private ImageIcon[] CLUBS = new ImageIcon[13];
	private JLabel pScoreBoard = new JLabel();
	private JLabel cScoreBoard = new JLabel();
	private JLabel[] pShowCard = new JLabel[3];
	private JLabel[] cShowCard = new JLabel[3];
	
	private HumanPlayer humanPlayer;
	private ComputerPlayer computerPlayer;
	private Dealer dealer;
	
	public GameBoard() {
		humanPlayer = new HumanPlayer(11, "player");
		computerPlayer = new ComputerPlayer(11);
		dealer = new Dealer();
		
		cardImgSetUp("SPADES", SPADES);
		cardImgSetUp("HEARTS", HEARTS);
		cardImgSetUp("DIAMONDS", DIAMONDS);
		cardImgSetUp("CLUBS", CLUBS);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JPanel southPanel = new JPanel(); //southPanel안에 cScoreBoard와 cCardPanel이 들어감
		JPanel cCardPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel northPanel = new JPanel(); //northPanel안에 pScoreBoard와 pCardPanel이 들어감
		JPanel pCardPanel = new JPanel();
		
		southPanel.setLayout(new BorderLayout());
		
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
		for (JLabel cSCard : cShowCard) {
			cSCard = new JLabel();
			cSCard.setIcon(imageIconImageSetSize(new ImageIcon("./card_image/DEFAULT1.jpg"), 
					cardWidth, cardHeight));
			cCardPanel.add(cSCard);
		}
		
		//southPanel에 추가
		southPanel.add(cScoreBoard, BorderLayout.NORTH);
		southPanel.add(cCardPanel, BorderLayout.CENTER);
		
		//buttonPanel 설정 후 버튼 추가
		buttonPanel.setLayout(new GridLayout(1, 2));
		JButton moreButton = new MoreButton("MORE", dealer, humanPlayer, this);
		JButton stopButton = new StopButton("STOP", this);
		buttonPanel.add(moreButton);
		buttonPanel.add(stopButton);
		
		northPanel.setLayout(new BorderLayout());
		
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
		for (JLabel pSCard : pShowCard) {
			pSCard = new JLabel();
			pSCard.setIcon(imageIconImageSetSize(new ImageIcon("./card_image/DEFAULT2.jpg"), 
					cardWidth, cardHeight));
			pCardPanel.add(pSCard);
		}
		
		//northPanel에 추가
		northPanel.add(pScoreBoard, BorderLayout.SOUTH);
		northPanel.add(pCardPanel, BorderLayout.CENTER);
		
		cp.add(southPanel, BorderLayout.NORTH);
		cp.add(buttonPanel, BorderLayout.CENTER);
		cp.add(northPanel, BorderLayout.SOUTH);
		
		//2장씩 나눠주고 시작
		//플레이어가 블랙잭인 경우 바로 GameOver로 감
		dealer.dealOneTo(humanPlayer);
		dealer.dealOneTo(computerPlayer);
		dealer.dealOneTo(humanPlayer);
		dealer.dealOneTo(computerPlayer);
		if (scoreCheck() == 1)
			GameOver();
		
		setTitle("BLACKJACK_GUI");
		setSize(gameBoardWidth, gameBoardHeight);
		setVisible(true);
		setLocationRelativeTo(null); //게임 화면 스크린 정중앙 위치
		setResizable(false); //게임 화면 리사이즈 불가
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void GameOver() {
		
	}
	
	public void update() {
		
	}
	
	/** 
	 * 현재 플레이어의 상태(속행가능, 블랙잭, 버스트)를 판단해주는 메소드
	 * @return 속행가능: 0, 블랙잭: 1, 버스트: 2
	 */
	private int scoreCheck() {
		return 0; //임시로 0을 리턴
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
