package jpubs.study012;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcedureTest {
	
	Connection con;
	static {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		try {
			con = DriverManager.getConnection(url, "javadb", "javadb");
			System.out.println("Connection Success!");
		} catch (SQLException e) {
			
		}
	}
	
	public void insert() {
		int count = 0;
		connect();
		PreparedStatement pstmt = null;
		String sql = "insert into member_proc values(?, ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "Alpha");
			pstmt.setString(2, "1234");
			pstmt.setString(3, "AI");
			pstmt.setInt(4, 20);
			pstmt.setString(5, "LA");
			pstmt.setString(6, "go@ai.com");
			count = pstmt.executeUpdate();
			if(count > 0) {
				System.out.println("insert success!!");
			} else {
				System.out.println("insert fail!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void select() {
		connect();
		PreparedStatement pstmt = null;
		String sql = "select * from member_proc";
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString(1);
				String passwd = rs.getString(2);
				String name = rs.getString(3);
				int age = rs.getInt(4);
				String addr = rs.getString(5);
				String email = rs.getString(6);
				System.out.println("아이디: " + id + " 비밀번호: " + passwd +  " 이름: " + name +  " 나이: " + age +  " 주소: " + addr +  " 이메일: " + email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//저장 프로시저 호출 후 데이터 입력
	public void insertMember() {
		connect();
		String myId = "procedure";
		String myPasswd = "1234";
		String myName = "홍길동";
		int myAge = 19;
		String myAddr = "강원도";
		String myEmail = "hong@abc.com";
		CallableStatement cs = null;
		int count = 0;
		try {
			cs = con.prepareCall("{call insertMember(?,?,?,?,?,?)}");
			cs.setString(1, myId);
			cs.setString(2, myPasswd);
			cs.setString(3, myName);
			cs.setInt(4, myAge);
			cs.setString(5, myAddr);
			cs.setString(6, myEmail);
			count = cs.executeUpdate();
			if(count > 0) {
				System.out.println("insert success!!");
			} else {
				System.out.println("insert fail!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			cs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		ProcedureTest pt = new ProcedureTest();
		System.out.println("*** 저장프로시저 호출 전 데이터 ***");
		pt.insert();
		pt.select();
		System.out.println("===== 저장프로시저 호출 후 데이터 ===");
		pt.insertMember();
		pt.select();
	}

}
