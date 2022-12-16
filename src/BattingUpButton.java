import javax.swing.*;
import java.awt.event.*;
public class BattingUpButton extends JButton implements ActionListener {
	private JLabel batting_score;
	private HumanPlayer human_player;
	
	/**
	 * BattingUpButton 초기설정
	 * @param buttonName 버튼 텍스트
	 * @param b 배팅 칩 보드 라벨
	 * @param hp HumanPlayer 객체
	 */
	public BattingUpButton(String button_name, JLabel b, HumanPlayer hp) {
		super(button_name);
		batting_score = b;
		human_player = hp;
		addActionListener(this);
	}
	
	/**
	 * 눌렀을 때, 배팅 칩이 보유한 칩 미만이라면 1씩 배팅 카운트를 올린다.  
	 */
	public void actionPerformed(ActionEvent e) {
		int n = Integer.parseInt(batting_score.getText());
		if (human_player.getChip() > n) n+=1;
		String s = Integer.toString(n);
		batting_score.setText(s);
	}
}
