import javax.swing.*;
import java.awt.event.*;
public class EndButton extends JButton implements ActionListener {
	
	public EndButton(String buttonName) {
		super(buttonName);
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		System.exit(1);
	}
}
