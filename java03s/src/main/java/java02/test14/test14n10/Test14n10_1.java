package java02.test14.test14n10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Test14n10_1 {
  Scanner scanner;

  private String[] promptCommand() {
    System.out.print("명령>");
    String[] token = scanner.nextLine().split(" ");
    return token;
  }  
  
public void connection() {
  loop:
    while(true){
      try {
        String[] token = promptCommand();
        
        
        if (token[0].equals("exit"))
          break loop;
      } catch (Exception e){
        e.printStackTrace();
      }
    }
}
  public static void main(String[] args) throws Exception {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      // java.sql.Driver 구현체 로딩한다.
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("JDBS Driver Loading Complete");

      // DriverManager에게 Connection 객체를 부탁한다.
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/studydb" + // jdbc 접속을 위한 URML정보
          "?useUnicode=true&characterEncoding=utf8",
          "study", // 사용자 아이디
          "study"); // 사용자 암호
      System.out.println("DBMS에 연결됨");

      // Statement 객체 얻기
      stmt = con.createStatement();
      System.out.println("Statement 객체 준비 완료");

      // list 출력
      // select 문 실행하기
      rs = stmt.executeQuery("SELECT * FROM PRODUCTS");

      // 저장하기 add 실행시
      stmt.executeUpdate("INSERT INTO PRODUCTS(PNAME, QTY, MKNO"
          + "VALUES('%s',%d, %d)");//받아야해~
      System.out.println("저장하시겠습니까? (y/n)");
      System.out.println("저장하였습니다.");
      System.out.println("저장 취소하였습니다.");

      // update 변경하기.
      stmt.executeUpdate("UPDATE PRODUCTS SET" 
          + " PNAME='%s', QTY=%d WHERE PNO=%d");
      System.out.println("변경하였습니다.");
      
      // delete 삭제하기
      stmt.executeUpdate("DELETE FROM PRODUCTS"
          + "WHERE PNO in(%d)");
      
      // 5. 결과 가져오기
      // => 현재 경과를 가져온 상태가 아님!
      /*      while (rs.next()) {
        // 결과를 가져왔다면 데이터는 ResultSet 객체에 들어있다. 
        System.out.print(rs.getInt(1) + ",");
        System.out.print(rs.getString(2) + ",");
        System.out.print(rs.getInt(3) + ",");
        System.out.println(rs.getInt(4));

      }*/
    } catch (Exception e) {
      e.printStackTrace();

    } finally {
      try {rs.close(); } catch (Exception ex) {}
      System.out.println("ResultSet 객체의 자원을 해제함");

      try {stmt.close(); } catch (Exception e) {}
      System.out.println("Statement 객체의 자원을 해제함");

      try {con.close(); } catch (Exception e) {}
      System.out.println("DBMS와 연결 끊음");
    }
  }

}
