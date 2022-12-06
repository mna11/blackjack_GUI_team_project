import javax.swing.*;
import java.awt.event.*;
public class MoreButton extends JButton implements ActionListener{
	GameBoard gameBoard;
	Dealer dealer;
	HumanPlayer humanPlayer;
	
	public MoreButton(String buttonName, Dealer d, HumanPlayer hP, GameBoard f) {
		super(buttonName);
		gameBoard = f;
		dealer = d;
		humanPlayer = hP;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		dealer.dealOneTo(humanPlayer);
		gameBoard.update();
	}

}
