import java.io.*;
import java.util.*;

public class WriteFile {
	private PrintWriter outfile;
	
	/**
	 * 파일을 작성할 용도로 pData.csv를 연다.
	 */
	public WriteFile() {
		try {
			outfile = new PrintWriter(new FileWriter("pData.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * pData.csv를 닫는다.
	 */
	public void close() {
		outfile.close();
	}
	
	/**
	 * 파일에 한줄로 name, chips, password를 작성해준다. 
	 * @param name 플레이어 이름
	 * @param chip 칩 수
	 * @param password 패스워드 
	 */
	public void saveData(String name, int chip, String password) {
        outfile.println(name + "," + chip + "," + password);
    }
	
	/**
	 * 파일에 한줄로 s를 작성해준다.
	 * @param s 작성할 문자열
	 */
	public void printCheck(String s) {
		outfile.println(s);
	}
}
