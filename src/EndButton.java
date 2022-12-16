import javax.swing.*;
import java.awt.event.*;
public class EndButton extends JButton implements ActionListener {
	private GameBoard game_board;
	
	/**
	 * EndButton 초기 설정
	 * @param button_name 버튼 텍스트
	 * @param gb GameBoard 객체
	 */
	public EndButton(String button_name, GameBoard gb) {
		super(button_name);
		game_board = gb;
		addActionListener(this);
	}
	
	/**
	 * 눌렀을 때 gameBoard.pressEnd()를 실행함.
	 */
	public void actionPerformed(ActionEvent e) {
		game_board.pressEnd();
	}
}
