import javax.swing.*;
import java.awt.event.*;
public class StartButton extends JButton implements ActionListener {
	private GameBoard gameBoard;
	private JLabel battingBoard;
	
	public StartButton(String buttonName, GameBoard gb, JLabel bb) {
		super(buttonName);
		gameBoard = gb;
		battingBoard = bb;
		addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		int n = Integer.parseInt(battingBoard.getText());
		gameBoard.showFirstHC();
		gameBoard.setButtonEnable(true);
		gameBoard.setBattingChip(n);
	}
}
