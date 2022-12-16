import javax.swing.*;
import java.awt.event.*;
public class StartButton extends JButton implements ActionListener {
	private GameBoard game_board;
	private JLabel batting_board;
	
	/**
	 * StartButton은 배팅을 다 하고 게임을 시작할 때, 누른다.
	 * @param button_name 버튼 텍스트
	 * @param gb GameBoard 객체
	 * @param bb 배팅보드 라벨
	 */
	public StartButton(String button_name, GameBoard gb, JLabel bb) {
		super(button_name);
		game_board = gb;
		batting_board = bb;
		addActionListener(this);
	}
	
	/**
	 * 버튼을 눌렀을 때, 게임 시작 세팅을 해준다.
	 */
	public void actionPerformed(ActionEvent e) {
		int n = Integer.parseInt(batting_board.getText());
		game_board.setButtonEnable(true);
		game_board.setBattingChip(n);
		game_board.showFirstHC();
	}
}
