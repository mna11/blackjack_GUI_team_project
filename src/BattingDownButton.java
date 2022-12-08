import javax.swing.*;
import java.awt.event.*;
public class BattingDownButton extends JButton implements ActionListener {
	private JLabel battingScore;
	
	public BattingDownButton(String buttonName, JLabel b) {
		super(buttonName);
		battingScore = b;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		int n = Integer.parseInt(battingScore.getText());
		if (n >= 1) n -= 1;
		String s = Integer.toString(n);
		battingScore.setText(s);
	}
}
