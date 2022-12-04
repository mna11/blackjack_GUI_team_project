import javax.swing.*;
import java.awt.event.*;
public class MoreButton extends JButton implements ActionListener{
	GameBoard gameBoard;
	Dealer dealer;
	HumanPlayer humanPlayer;
	
	public MoreButton(String buttonName, Dealer d, HumanPlayer humanplayer, GameBoard f) {
		super(buttonName);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

}
