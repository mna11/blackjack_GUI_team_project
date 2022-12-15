import javax.swing.*;
import java.awt.event.*;
public class BattingDownButton extends JButton implements ActionListener {
	private JLabel battingScore;
	
	/**
	 * BattingDownButton 초기설정
	 * @param buttonName 버튼 텍스트
	 * @param b 배팅 스코어 라벨
	 */
	public BattingDownButton(String buttonName, JLabel b) {
		super(buttonName);
		battingScore = b;
		addActionListener(this);
	}
	
	/**
	 * 눌렀을 때, 배팅 칩이 0 초과라면 1씩 배팅 카운트를 줄인다.
	 */
	public void actionPerformed(ActionEvent e) {
		int n = Integer.parseInt(battingScore.getText());
		if (n >= 1) n -= 1;
		String s = Integer.toString(n);
		battingScore.setText(s);
	}
}
