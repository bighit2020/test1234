package clob.ex2;
//작동됨
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OracleClob2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// 오라클 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		 
		// 오라클 연결
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "java04", "java04");
		 
		// 파일 정보
		File file = new File("aa.txt");
		InputStream is = new FileInputStream(file);
		int fileSize = (int)file.length();
		 
		// 입력쿼리 구성
		String insertSql = "INSERT INTO TEST_TBL2 VALUES( 2, 'User', 'Password', ? )";
		PreparedStatement psmt = conn.prepareStatement(insertSql);
		 
		// [스트림을 이용하는 방법]
		psmt.setBinaryStream(1, is, fileSize);
		 
		// [byte배열을 이용하는 방법]
		byte[] buffer = new byte[fileSize];
		is.read(buffer);
		psmt.setBytes(1, buffer);
		 
		// 입력
		int insertCount = psmt.executeUpdate();
		psmt.close();
		 
		// 연결해제
		conn.close();


	}

}
