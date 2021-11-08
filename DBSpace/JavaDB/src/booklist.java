//madang DB의 Book 테이블에 저장된 도서를 읽어와 출력하는 프로그램

import java.sql.*;

//
public class booklist {
	Connection con;  //java.sql의 Connection 객체 con을 선언
	public booklist() {
	 /* 접속변수 초기화. url은 자바 드라이버 이름,DBMS(jdbc.oracle:thin:),
		호스트명(localhost),접속포트(1521),데이터베이스 이름(orcl) 순.     */
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userid = "madang";
		String pwd = "madang";

		try { /* 드라이버를 찾는 과정 */
			/*Class.forName()으로 드라이버 로딩, 드라이버 이름을 Class.forName에 입력*/
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try { /* 데이터베이스를 연결하는 과정 */
			/*접속객체 con을 DriverManager.getConnection함수로 생성*/
			System.out.println("데이터베이스 연결 준비 ...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void sqlRun() {
		String query = "SELECT * FROM Book"; 
		try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
		/*SQL 실행객체 stmt 생성, stmt객체를 executeQuery(query)로 호출하여 결과를 ResultSet에 반환한다.
			rs는 'SELECT * FROM Book' 실행결과를 가지고 있다.*/
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("BOOKNO   BOOKNAME \t PUBLISHER \t PRICE");
			System.out.println("--------------------------------------------------");
			
			/*rs 객체에 next()를 적용하면 결과 테이블에서 한행씩 반환된다. 반환된 행을 rs.getInt()에
			적용하면 첫째열값이 정수로 변환된다. 나머지 열값들도 getInt나 getString함수로 값을 받는다.*/		
			while (rs.next()) {
				//System.out.print("  " + rs.getInt(1));//모두 가능
				System.out.print("  " + rs.getString(1));//String으로 가능
				System.out.print("\t  " + rs.getString(2));
				System.out.print("\t      " + rs.getString(3));
				//System.out.println("\t " + rs.getInt(4));
				System.out.println("\t " + rs.getString(4));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*main함수에서 booklist 객체에 so를 생성하고 sqlRun()메소드를 호출*/
	public static void main(String args[]) {
		booklist so = new booklist();
		so.sqlRun();
	}
}
