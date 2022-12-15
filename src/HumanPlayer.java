
import javax.swing.*;

public class HumanPlayer extends CardPlayer {
	
	private String name;	
	private int chips = 20;
	private GameBoard gameBoard;
	private int battingChip; 
	
	public HumanPlayer(int max_cards, String n, GameBoard gb) {
		super(max_cards);
		gameBoard = gb;
		name = n;
	}
	
	public boolean wantsACard() {
		String response = JOptionPane.showInputDialog("한장 더 드릴까요? (Y/N)");
		return response.equals("Y") || response.equals("y");
	}
	
	public void youWin() {
		battingChip = gameBoard.getBattingChip();
		
		chips += battingChip * 2;
		gameBoard.setBattingChip(0);
	}
	
	public void youWinBlackjack() {
		battingChip = gameBoard.getBattingChip();
		chips += battingChip + 10;
		gameBoard.setBattingChip(0);
	}
	
	public void youLose() {
		gameBoard.setBattingChip(0);
	}
	
	public void youDraw() {
		battingChip = gameBoard.getBattingChip();
		chips += battingChip;
		gameBoard.setBattingChip(0);
	}
	
	
	public void setChips(int n) {
		chips = n;
	}
	
	public int countChips() {
		return chips;
	}
}
