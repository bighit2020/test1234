package p03.java;
//자바 실행전에 반드시 SQL Developer에서 저장 프로시저를 실행(컴파일)한후에 자바실행을 해야한다.
import java.sql.*;
import java.io.*;
import java.util.*;

public class CallInoutProc {
   public static void main(String[] args) throws SQLException {
      DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
      // DB 접속
      Connection conn = DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:orcl", "java04", "java04");
      // 변수 선언
      String p1value = new String("a");
      String p2value = new String("b");
      String p3value;
      
//      Oracle 에서 입력된 stored procedure 를 java에서 실행해서 결과를 얻고 싶으면, 
//      Statement 대신에 CallableStatement class를 사용하여 호출하면 된다.
      // Prepare statement
      CallableStatement cs = conn.prepareCall("{call javatest(?,?,?)}");
      // IN parameter 값을 넘기기 위해 setString 함수를 사용
      cs.setString(1,p1value);
      cs.setString(2,p2value);
      // OUT parameter에 대한 정보를 registerOutParameter 함수를 이용하여 설정 
      cs.registerOutParameter(2,Types.VARCHAR);
      cs.registerOutParameter(3,Types.VARCHAR);
      // Statement 수행
      cs.execute();
      // 수행결과 data를 가져옴. 
      p2value = cs.getString(2);
      p3value = cs.getString(3);
      // 수행결과 출력
      System.out.println("p2 :" + p2value);
      System.out.println("p3 :" + p3value);
   }
}

