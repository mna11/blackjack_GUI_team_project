import javax.swing.*;
import java.awt.event.*;
public class EndButton extends JButton implements ActionListener {
	private GameBoard gameBoard;
	
	/**
	 * EndButton 초기 설정
	 * @param buttonName 버튼 텍스트
	 * @param gb GameBoard 객체
	 */
	public EndButton(String buttonName, GameBoard gb) {
		super(buttonName);
		gameBoard = gb;
		addActionListener(this);
	}
	
	/**
	 * 눌렀을 때 gameBoard.pressEnd()를 실행함.
	 */
	public void actionPerformed(ActionEvent e) {
		gameBoard.pressEnd();
	}
}
