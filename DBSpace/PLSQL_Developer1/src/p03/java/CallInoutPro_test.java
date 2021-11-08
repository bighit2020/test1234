package p03.java;
//자바 실행전에 반드시 SQL Developer에서 저장 프로시저를 실행(컴파일)한후에 자바실행을 해야한다.
import java.sql.*;
import java.io.*;
import java.util.*;

public class CallInoutPro_test {
   public static void main(String[] args) throws SQLException {
      DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
      Connection conn = DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:orcl", "java04", "java04");
    
      String p1value = new String("a");
      String p2value = new String("b");
      String p3value;
      
//Oracle 에서 입력된 stored procedure javatest 실행     
      CallableStatement cs = conn.prepareCall("{call javatest(?,?,?)}");
      
      cs.setString(1,p1value);
      cs.setString(2,p2value);
      
      cs.registerOutParameter(2,Types.VARCHAR);
      cs.registerOutParameter(3,Types.VARCHAR);
      
      cs.execute();
       
      p2value = cs.getString(2);
      p3value = cs.getString(3);
      
      System.out.println("p2 :" + p2value);
      System.out.println("p3 :" + p3value);
   }
}

