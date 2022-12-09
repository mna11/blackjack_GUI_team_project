import javax.swing.*;
import java.awt.event.*;

public class StopButton extends JButton implements ActionListener{
	private GameBoard gameBoard;
	private boolean isFirst = true;
	
	public StopButton(String buttonName, GameBoard f) {
		super(buttonName);
		gameBoard = f;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (isFirst) {
			isFirst = false;
			gameBoard.gameOver();
		}
		else {
			gameBoard.setButtonEnable(false);
			gameBoard.init();
			isFirst = true;
		}
	}
	
	public void setIsFirst(boolean b) {
		isFirst = b;
	}
}
