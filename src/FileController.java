import javax.swing.JOptionPane;

public class FileController {
	private HumanPlayer human_player;
	private String p_name;
	private String file_contents = "";
	private int chip = 20;
	private String password;
	private boolean new_player = true;
	
	/**
	 * FileController가 만들어질때, 파일을 다 읽어오고, 비밀번호 체크도 해준다.
	 * @param hp HumanPlayer 객체
	 */
	public FileController(HumanPlayer hp) {
		human_player = hp;
		p_name = human_player.getName();
		ReadFile reader = new ReadFile();
		while(reader.getNextRecord()) {
			if(p_name.equals(reader.getName())) {
				chip = reader.getChip();
				password = reader.getPassword();
				new_player = false;
			}
			file_contents += reader.getName() + "," + reader.getChip() + "," + reader.getPassword() + "\n";
		}
		if (new_player) {
			password = JOptionPane.showInputDialog("패스워드를 설정해주세요.");
		}
		else {
			int test = 2;
			String s = JOptionPane.showInputDialog("패스워드를 입력해주세요.");
			while(!password.equals(s)) {
				test--;
				s = JOptionPane.showInputDialog("정보와 다릅니다.\n패스워드를 다시 입력해주세요.");
				if (test == 0) {
					JOptionPane.showMessageDialog(null, "임시 차단되었습니다.", "접근 실패", JOptionPane.WARNING_MESSAGE);
					System.exit(1);
				}
			}
			JOptionPane.showMessageDialog(null, "확인되었습니다.");
		}
		human_player.setChip(chip);
		reader.close();
	}
	
	/**
	 * EndButton이 눌렸을 때, 정보를 갱신해서 파일을 작성해준다.
	 */
	public void endSoWrite() {
		WriteFile writer = new WriteFile();
		chip = human_player.getChip();
		String[] line = file_contents.split("\n");
		for (int i = 0; i < line.length; i++) {
			if(line[i] == "") continue;
			String[] contents = line[i].split(",");
			if (contents[0].equals(p_name)) {
				writer.saveData(p_name, chip, password);
			}
			else {
				writer.saveData(contents[0], Integer.parseInt(contents[1]), contents[2]);
			}
		}
		if (new_player) {
			writer.saveData(p_name, chip, password);
		}
		writer.printCheck("!");
		writer.close();
	}
}
