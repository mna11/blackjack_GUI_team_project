import javax.swing.JOptionPane;

public class FileController {
	private HumanPlayer humanPlayer;
	private String pName;
	private String fileContents = "";
	private int chip = 20;
	private String password;
	private boolean newPlayer = true;
	
	/**
	 * FileController가 만들어질때, 파일을 다 읽어오고, 비밀번호 체크도 해준다.
	 * @param hp HumanPlayer 객체
	 */
	public FileController(HumanPlayer hp) {
		humanPlayer = hp;
		pName = humanPlayer.getName();
		ReadFile reader = new ReadFile();
		while(reader.getNextRecord()) {
			if(pName.equals(reader.getName())) {
				chip = reader.getChip();
				password = reader.getPassword();
				newPlayer = false;
			}
			fileContents += reader.getName() + "," + reader.getChip() + "," + reader.getPassword() + "\n";
		}
		if (newPlayer) {
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
		humanPlayer.setChip(chip);
		reader.close();
	}
	
	/**
	 * EndButton이 눌렸을 때, 정보를 갱신해서 파일을 작성해준다.
	 */
	public void EndSoWrite() {
		WriteFile writer = new WriteFile();
		chip = humanPlayer.getChip();
		String[] line = fileContents.split("\n");
		for (int i = 0; i < line.length; i++) {
			if(line[i] == "") continue;
			String[] contents = line[i].split(",");
			if (contents[0].equals(pName)) {
				writer.saveData(pName, chip, password);
			}
			else {
				writer.saveData(contents[0], Integer.parseInt(contents[1]), contents[2]);
			}
		}
		if (newPlayer) {
			writer.saveData(pName, chip, password);
		}
		writer.printCheck("!");
		writer.close();
	}
}
