
import javax.swing.*;

public class HumanPlayer extends CardPlayer {
	
	private String name = "";	
	private int chip = 20;
	private GameBoard gameBoard;
	private int battingChip; 
	
	/**
	 * HumanPlayer 초기 설정
	 * @param max_cards 최대 보유할 수 있는 카드 수
	 * @param n player 이름
	 * @param gb GameBoard 객체
	 */
	public HumanPlayer(int max_cards, String n, GameBoard gb) {
		super(max_cards);
		gameBoard = gb;
		name = n;
	}
	
	public boolean wantsACard() {
		String response = JOptionPane.showInputDialog("한장 더 드릴까요? (Y/N)");
		return response.equals("Y") || response.equals("y");
	}
	
	/**
	 * 이겼을 때, 배팅칩의 2배로 돌려준다.
	 */
	public void youWin() {
		battingChip = gameBoard.getBattingChip();
		chip += battingChip * 2;
		gameBoard.setBattingChip(0);
	}
	
	/**
	 * 블랙잭인 경우, 배팅칩의 2배 + 10개로 돌려준다.
	 */
	public void youWinBlackjack() {
		battingChip = gameBoard.getBattingChip();
		chip += battingChip * 2 + 10;
		gameBoard.setBattingChip(0);
	}
	
	/**
	 * 진 경우, 배팅칩은 돌려주지 않는다.
	 */
	public void youLose() {
		gameBoard.setBattingChip(0);
	}
	
	/**
	 * 비긴 경우, 배팅칩을 그대로 돌려준다.
	 */
	public void youDraw() {
		battingChip = gameBoard.getBattingChip();
		chip += battingChip;
		gameBoard.setBattingChip(0);
	}
	
	/**
	 * 플레이어 이름을 반환함
	 * @return 플레이어 이름
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 현재 chip 개수를 설정한다.
	 * @param n 설정할 chip 개수
	 */
	public void setChip(int n) {
		chip = n;
	}
	
	/**
	 * 현재 chip 개수를 반환한다.
	 * @return chip 개수
	 */
	public int getChip() {
		return chip;
	}
}
