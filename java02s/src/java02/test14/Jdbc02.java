/*
Connection 객체 얻기
=> java.sql.Driver 구현체를 통해서 얻을 수 있다.
=> DriverManager에게 부탁하면, 
   DriverManager가 우리를 대신하여 java.sql.Driver 객체에게 요구한다.
 */
package java02.test14;

import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbc02 {

  public static void main(String[] args) throws Exception {
    Connection con = null;
    //참조변수가 인터페이스일 때는 이 인터페이스를 구현한 클래스들의 주소를 담을 수 있다는 뜻.
    // 인터페이스 객체 못담음. 인터페이스는 객체 업슴. 
    // 인터페이스 객체라 말하지만, 뜻은 이 인터페이스를 구현한 어떤 클래스의 객체!
    
    try {
    // 1. java.sql.Driver 구현체 로딩한다.
    Class.forName("com.mysql.jdbc.Driver");
    System.out.println("JDBC 드라이버 로딩됨");
    
    // 2. DriverManager에게 Connection 객체를 부탁한다.
    con = DriverManager.getConnection(
        /* jdbc 접속을 위한 URML 정보. DBMS마다 형식이 약간씩 다르다. */
        "jdbc:mysql://localhost:3306/studydb", 
        "study", /* 사용자 아이디 */ 
        "study"); /* 사용자 암호 */
    System.out.println("DBMS에 연결됨");
    
    } catch (Exception ex) {
      ex.printStackTrace();
      
    } finally {
      try {con.close(); } catch (Exception ex) {
      System.out.println("DBMS와 연결 끊음");
      }
    }

  }

}
