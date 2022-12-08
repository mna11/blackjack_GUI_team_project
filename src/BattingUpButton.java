import javax.swing.*;
import java.awt.event.*;
public class BattingUpButton extends JButton implements ActionListener {
	private JLabel battingScore;
	private HumanPlayer humanPlayer;
	public BattingUpButton(String buttonName, JLabel b, HumanPlayer hp) {
		super(buttonName);
		battingScore =b;
		humanPlayer = hp;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		int n = Integer.parseInt(battingScore.getText());
		if (humanPlayer.countChips() > n) n+=1;
		String s = Integer.toString(n);
		battingScore.setText(s);
	}
}
