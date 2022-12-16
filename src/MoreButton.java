import javax.swing.*;
import java.awt.event.*;
public class MoreButton extends JButton implements ActionListener{
	GameBoard game_board;
	Dealer dealer;
	HumanPlayer human_player;
	
	/**
	 * MoreButton 초기 설정
	 * @param button_name 버튼 텍스트
	 * @param d Dealer 객체
	 * @param hp HumanPlayer 객체
	 * @param f GameBoard 객체
	 */
	public MoreButton(String button_name, Dealer d, HumanPlayer hp, GameBoard f) {
		super(button_name);
		game_board = f;
		dealer = d;
		human_player = hp;
		addActionListener(this);
	}
	
	/**
	 * 눌렀을 때, HumanPlayer 객체에게 카드 한장을 주고 GameBoard.update()를 호출한다.
	 */
	public void actionPerformed(ActionEvent e) {
		dealer.dealOneTo(human_player);
		game_board.update();
	}

}
