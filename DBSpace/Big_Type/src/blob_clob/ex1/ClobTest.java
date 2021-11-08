package blob_clob.ex1;

import java.io.CharArrayReader;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleResultSet;
import oracle.sql.CLOB;


public class ClobTest {

	Connection dbConn;
	StringBuffer sb=null;
	Statement stmt = null;
	ResultSet rs = null;
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
			dbConn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",	"java04", "java04");
			System.out.println("Connection Success!");
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	}

	public void insert(){
	       // UPDATE 또는 INSERT 명령으로 DB 에 공간 확보
	     String query = "UPDATE TABLE SET CLOB_DATA = EMPTY_CLOB() " ;
	     stmt.executeUpdate(query);
	     // 그런 다음 다시 요놈을 다시 SELECT
	     query = "SELECT CLOB_DATA  FROM TABLE WHERE ~ " ;   
	     stmt = dbConn.createStatement(); 
	     rs = stmt.executeQuery(query);
	     if(rs.next()) {
	          CLOB clob = null;
	          Writer writer = null;
	          Reader src = null;
	          char[] buffer = null;
	          int read = 0;  
	          clob = ((OracleResultSet)rs).getCLOB(1);         
	          writer = clob.getCharacterOutputStream();
	          // str -> DB에 넣을 내용
	          src = new CharArrayReader(str.toCharArray()); 
	          buffer = new char[1024]; 
	          read = 0;
	          while ( (read = src.read(buffer,0,1024)) != -1) { 
	               writer.write(buffer, 0, read); // write clob. 
	          }
	          src.close();         
	          writer.close();
	     }
	     dbConn.commit();
	     dbConn.setAutoCommit(true);

	
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
