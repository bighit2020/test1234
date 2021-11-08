package p04.etc;

import java.sql.*; /** * * @author CODEZIP * */ 
public class Procedure { /** * @param args * @throws ClassNotFoundException */ 
public static void main(String[] args) throws ClassNotFoundException { 
// TODO Auto-generated method stub 
 try { Class.forName("oracle.jdbc.driver.OracleDriver");
 String url = "jdbc:oracle:thin:@192.168.1.1:1521:DBNAME";
 String call = "{ ? = call LOOKUP_FUCT(?,?,?) }"; 
 Connection con = DriverManager.getConnection(url,"codezip","codezip"); 
  CallableStatement cstmt; cstmt = con.prepareCall(call); 
  cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR); cstmt.setString(2, "28"); 
   cstmt.setString(3,"SEARCH"); cstmt.setString(4,"Y"); cstmt.executeUpdate(); 
   String val = cstmt.getString(1); 
   System.out.println(val); 
   cstmt.close(); 
   con.close(); 
} catch (SQLException e) { // TODO Auto-generated 
  //catch block e.printStackTrace(); 
} 
} 
} 
