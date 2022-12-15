import javax.swing.*;
import java.awt.event.*;
public class MoreButton extends JButton implements ActionListener{
	GameBoard gameBoard;
	Dealer dealer;
	HumanPlayer humanPlayer;
	
	/**
	 * MoreButton 초기 설정
	 * @param buttonName 버튼 텍스트
	 * @param d Dealer 객체
	 * @param hP HumanPlayer 객체
	 * @param f GameBoard 객체
	 */
	public MoreButton(String buttonName, Dealer d, HumanPlayer hP, GameBoard f) {
		super(buttonName);
		gameBoard = f;
		dealer = d;
		humanPlayer = hP;
		addActionListener(this);
	}
	
	/**
	 * 눌렀을 때, HumanPlayer 객체에게 카드 한장을 주고 GameBoard.update()를 호출한다.
	 */
	public void actionPerformed(ActionEvent e) {
		dealer.dealOneTo(humanPlayer);
		gameBoard.update();
	}

}
