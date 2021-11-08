package clob.a01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClobTest {
	Connection con;
	StringBuffer sb=null;

	static{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException cne){
			cne.printStackTrace();
		}
	}

	public void connect(){
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",	"java04", "java04");
			System.out.println("Connection Success!");
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	}

	public void insert(){

		String sql="INSERT INTO clobtable (num,content) VALUES (1,?)";
		PreparedStatement pstmt=null;
		//ResultSet rs=null;

		try{		
			connect();
			sb=new StringBuffer();
			for(int i=0;i<=100;i++){// 반복 횟수가 적어야 한다.clob 타입에 사용될 것이 아니므로
				sb.append("홍길동");
			}

			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, sb.toString());//clob 타입을 사용하지 않으므로
			int count =	pstmt.executeUpdate();
			if(count > 0){
				System.out.println("insert success!");
			}
			else{
				System.out.println("insert fail");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				pstmt.close();
				con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public void select(){
		String sql = "SELECT * FROM clobtable";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println("번호 : " + rs.getString(1) + ",내용 : " 
						+ rs.getString(2));
			}			
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		finally{
			try{
				pstmt.close();
				con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]){
		ClobTest ct = new ClobTest();
		ct.insert();
		ct.select();
	}
}
