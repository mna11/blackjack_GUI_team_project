import javax.swing.*;
import java.awt.event.*;

public class StopButton extends JButton implements ActionListener{
	private GameBoard gameBoard;
	
	public StopButton(String buttonName, GameBoard f) {
		super(buttonName);
		gameBoard = f;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		gameBoard.gameOver();
	}
	
}
