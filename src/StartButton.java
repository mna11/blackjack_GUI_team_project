import javax.swing.*;
import java.awt.event.*;
public class StartButton extends JButton implements ActionListener {
	private GameBoard gameBoard;
	private JLabel battingBoard;
	
	/**
	 * StartButton은 배팅을 다 하고 게임을 시작할 때, 누른다.
	 * @param buttonName 버튼 텍스트
	 * @param gb GameBoard 객체
	 * @param bb 배팅보드 라벨
	 */
	public StartButton(String buttonName, GameBoard gb, JLabel bb) {
		super(buttonName);
		gameBoard = gb;
		battingBoard = bb;
		addActionListener(this);
	}
	
	/**
	 * 버튼을 눌렀을 때, 게임 시작 세팅을 해준다.
	 */
	public void actionPerformed(ActionEvent e) {
		int n = Integer.parseInt(battingBoard.getText());
		gameBoard.showFirstHC();
		gameBoard.setButtonEnable(true);
		gameBoard.setBattingChip(n);
	}
}
