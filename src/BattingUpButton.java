import javax.swing.*;
import java.awt.event.*;
public class BattingUpButton extends JButton implements ActionListener {
	private JLabel battingScore;
	private HumanPlayer humanPlayer;
	
	/**
	 * BattingUpButton 초기설정
	 * @param buttonName 버튼 텍스트
	 * @param b 배팅 칩 보드 라벨
	 * @param hp HumanPlayer 객체
	 */
	public BattingUpButton(String buttonName, JLabel b, HumanPlayer hp) {
		super(buttonName);
		battingScore = b;
		humanPlayer = hp;
		addActionListener(this);
	}
	
	/**
	 * 눌렀을 때, 배팅 칩이 보유한 칩 미만이라면 1씩 배팅 카운트를 올린다.  
	 */
	public void actionPerformed(ActionEvent e) {
		int n = Integer.parseInt(battingScore.getText());
		if (humanPlayer.getChip() > n) n+=1;
		String s = Integer.toString(n);
		battingScore.setText(s);
	}
}
