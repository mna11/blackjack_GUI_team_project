import java.io.*;
import java.util.*;

public class ReadFile {
	private BufferedReader infile;
	private final String EOF = "!";
	private String file_name = "pData.csv";
	private String pName;
	private String pPassword;
	private int pChip = -1;
	
	/**
	 * pData.csv를 연다.
	 */
	public ReadFile() {
		try {
			infile = new BufferedReader(new FileReader(file_name));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 읽은 라인의 chip 개수를 반환한다.
	 * @return 현재 읽은 라인의 chip 개수
	 */
	public int getChip() {
		return pChip;
	}
	
	/**
	 * 읽은 라인의 password를 반환한다.
	 * @return 현재 읽은 라인의 password
	 */
	public String getPassword() {
		return pPassword;
	}
	
	/**
	 * 읽은 라인의 플레이어 이름을 반환한다.
	 * @return 현재 읽은 라인의 플레이어 이름
	 */
	public String getName() {
		return pName;
	}
	
	/**
	 * pData.csv 파일을 닫는다.
	 */
	public void close() {
	       try { infile.close(); }
	       catch (IOException e) {
	           System.out.println("PayrollReader Warning - file close failed");
	       }
	}
	
	/**
	 * 다음 라인을 읽어서 플레이어 이름, 칩 수, 패스워드를 가져온다.
	 * @return 파일을 읽어 데이터를 가져온 경우 true, 이 외 false
	 */
	public boolean getNextRecord() {
        boolean result = false;
        try {
            if (infile.ready()) {
                String line = infile.readLine();
                StringTokenizer t = new StringTokenizer(line, ",");
                pName = t.nextToken().trim();
                if (!pName.equals(EOF)) {
                    if (t.countTokens() == 2) {
                    	pChip = Integer.parseInt(t.nextToken().trim());
                    	pPassword = t.nextToken().trim();
                    	result = true;
                    }
                    else {
                        throw new RuntimeException(line);
                    }
                }
                else 
                	return result;
            }
        }
        catch (IOException e) {
            System.out.println("PayrollReader Error : " + e.getMessage());
        }
        catch (RuntimeException e) {
            System.out.println("PayrollReader Error - bad record format: " + e.getMessage() + " Skipping");
            result = getNextRecord();
        }
        return result;
    }
}
