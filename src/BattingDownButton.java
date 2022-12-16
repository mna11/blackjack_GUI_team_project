import javax.swing.*;
import java.awt.event.*;
public class BattingDownButton extends JButton implements ActionListener {
	private JLabel batting_score;
	
	/**
	 * BattingDownButton 초기설정
	 * @param button_name 버튼 텍스트
	 * @param b 배팅 칩 보드 라벨
	 */
	public BattingDownButton(String button_name, JLabel b) {
		super(button_name);
		batting_score = b;
		addActionListener(this);
	}
	
	/**
	 * 눌렀을 때, 배팅 칩이 0 초과라면 1씩 배팅 카운트를 줄인다.
	 */
	public void actionPerformed(ActionEvent e) {
		int n = Integer.parseInt(batting_score.getText());
		if (n >= 1) n -= 1;
		String s = Integer.toString(n);
		batting_score.setText(s);
	}
}
