import javax.swing.*;
import java.awt.event.*;

public class StopButton extends JButton implements ActionListener{
	private GameBoard game_board;
	private boolean is_first = true;
	
	/**
	 * Stop 버튼은 더 이상 카드를 받고싶지 않을 때, 누른다.
	 * @param buttonName 버튼 텍스트
	 * @param gb GameBoard 객체
	 */
	public StopButton(String button_name, GameBoard gb) {
		super(button_name);
		game_board = gb;
		addActionListener(this);
	}
	
	/**
	 * 첫번째 눌렀을 때는 더 이상 카드를 받고싶지 않다는 의사표현,
	 * 두번째 눌렀을 때는 새로운 게임을 다시 시작하겠다는 의사표현을 뜻한다.
	 */
	public void actionPerformed(ActionEvent e) {
		if (is_first) {
			is_first = false;
			game_board.gameOver();
		}
		else {
			game_board.setButtonEnable(false);
			game_board.init();
			is_first = true;
		}
	}
	
	/**
	 * 플레이어가 버스트 된 경우, is_first = false 세팅을 위한 것이다.
	 * @param b is_first를 세팅할 값
	 */
	public void setIsFirst(boolean b) {
		is_first = b;
	}
}
