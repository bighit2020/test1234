package jpub.ch24.ex1;
//args[0]에 8 입력하기

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlsqlFunctionUsingSelect {
  public static void main(String[] args) throws SQLException {
	  
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());// DB 접속    
    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "scott");
    int inValue = Integer.parseInt(args[0]);  // 변수 선언
    PreparedStatement stmt;
    stmt = conn.prepareStatement("SELECT factorial(?) FROM DUAL"); // SELECT문을 사용한 함수 호출
    stmt.setInt(1, inValue);// setXXX 함수를 사용하여 바인드 변수 값 설정
    ResultSet rs = stmt.executeQuery();// SELECT문 수행
    while (rs.next()){// 결과 출력
        System.out.println(inValue + "! = " + rs.getLong(1));
    }
  }
}
