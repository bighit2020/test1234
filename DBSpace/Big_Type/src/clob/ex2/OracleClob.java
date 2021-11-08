package clob.ex2;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.driver.*;


import oracle.jdbc.OracleResultSet;
import oracle.sql.CLOB;
 
public class OracleClob {
    
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        // Connect Oracle
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "java04", "java04");
 
        // Auto Commit false
        con.setAutoCommit(false);
 
        // Make Insert Query       
        String strQuery = "INSERT INTO TEST_TBL VALUES( ?, ?, ?, EMPTY_CLOB() )";
        PreparedStatement pstmt = con.prepareStatement(strQuery);
        pstmt.setInt(1, 4);
        pstmt.setString(2, "XXXXX");
        pstmt.setString(3, "XXXXX");
 
        // Insert Row
        int nRowCnt = pstmt.executeUpdate();
        pstmt.close();
 
        if( nRowCnt == 1 ) {
            // Make Select Query & Row Lock
            strQuery = "SELECT CONTENTS FROM TEST_TBL WHERE REC_KEY = ? FOR UPDATE";
            pstmt = con.prepareStatement(strQuery);
            pstmt.setInt(1, 4);
            ResultSet rs = pstmt.executeQuery();
 
            // Write CLOB Data
            String strCLOB = "XXXXX";
            if( rs.next() ) {
            	 CLOB clob = ((OracleResultSet)rs).getCLOB("CONTENTS");
                 @SuppressWarnings("deprecation")
				Writer writer = clob.getCharacterOutputStream();
                 Reader reader = new CharArrayReader(strCLOB.toCharArray());
                 char[] buffer = new char[1024];
                 int read = 0;
                  
                 while ((read = reader.read(buffer, 0, 1024)) != -1) {
                     writer.write(buffer, 0, read);
                 }
                 reader.close();
                 writer.close();
            }
             
            // Commit
            con.commit();
            con.setAutoCommit(true);
            rs.close();
        }
        con.close();
    }
}

