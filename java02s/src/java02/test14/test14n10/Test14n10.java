package java02.test14.test14n10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Test14n10 {
  static Scanner scanner = new Scanner(System.in);
  static Connection con = null;
  static Statement stmt = null;
  static ResultSet rs = null;

  public static void main(String[] args) {
    try {

      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("JDBC 드라이버 로딩됨");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb",
          "study", /* 사용자 아이디 */
          "study"); /* 사용자 암호 */
      System.out.println("DBMS에 연결됨");

      // 3. Statement 객체 얻기
      stmt = con.createStatement();
      System.out.println("Statement 객체 준비 완료.");

      loop: while (true) {
        try {
          String[] token = promptCommand();

          switch (token[0]) {
          case "add":
            doAdd();
            break;
          case "list":
            dolist();
            break;
          case "delete":
            doDelete(Integer.parseInt(token[1]));
            break;
          case "update":
            doUpdate(Integer.parseInt(token[1]));
            break;
          case "exit":
            break loop;
          default:
            System.out.println("이 명령어를 지원하지 않습니다.");
          }

        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("명령어 처리 중 오류 발생. 다시 시도해 주세요.");
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();

    } finally {
      try {scanner.close();} catch (Exception ex) {}
      try {rs.close();} catch (Exception ex) {}
      try {stmt.close();} catch (Exception ex) {}
      try {con.close();} catch (Exception ex) {}   
    }
  }

  private static void dolist() throws SQLException {
    rs = stmt.executeQuery("SELECT T1.PNO, PNAME, QTY, T2.MKNAME "
        + " FROM PRODUCTS T1 JOIN MAKERS T2 on T1.MKNO = T2.MKNO");
    //System.out.println("서버에 질의 완료. ResultSet 준비 완료.");

    System.out.printf("번호 제품명  \t\t      수량 제조사명\n");
    while(rs.next()){
      //결과를 가져왔다면 데이터는 resultSet 객체에 들어잇다.
      System.out.printf("%-3d %-20s %7d %-10s\n",rs.getInt("PNO"),
          rs.getString("PNAME"),rs.getInt("QTY"),rs.getString("MKNAME")  );
    }
  }


  private static void doAdd() throws Exception {

    System.out.println("추가");
    System.out.print("제품명 : ");
    String pname = scanner.nextLine();
    System.out.print("수량 : ");
    String qty = scanner.nextLine();
    System.out.print("제조사 : ");
    String mkno = scanner.nextLine();

    System.out.println("저장하시겠습니까?(y/n)");
    if(scanner.nextLine().equalsIgnoreCase("y")){
      stmt.executeUpdate("INSERT INTO PRODUCTS(PNAME,QTY,MKNO)"
          + " VALUES('" + pname + " ' ," + qty + " ," + mkno +" )");
      System.out.println("데이터 입력 완료");
    }
  }


  private static void doUpdate(int index) throws SQLException {
    String pname = null;
    int qty= 0, mkno= 0;
    rs = stmt.executeQuery("SELECT PNAME, QTY, MKNO FROM PRODUCTS WHERE PNO =" 
          + index);
    while(rs.next()){
      System.out.print("제품명("+rs.getString("PNAME") +")?");
      pname = scanner.nextLine();
        
      System.out.print("수량("+rs.getInt("QTY") +")?");
      qty = Integer.parseInt(scanner.nextLine());
      
      System.out.println("제조사("+rs.getInt("MKNO")+ ")?" );
      mkno = Integer.parseInt(scanner.nextLine());

    }
    stmt.executeUpdate("UPDATE PRODUCTS SET" +
        " PNAME = '" + pname+ " ', QTY = " + qty +
        " ,MKNO = " + mkno + " WHERE PNO = " + index);
    System.out.println("변경되었습니다.");
  }

  private static void doDelete(int index) throws SQLException {

    rs = stmt.executeQuery("SELECT PNAME FROM PRODUCTS WHERE PNO =" +index);
    if(rs.next()){
      System.out.println(rs.getString("pname") + "를 삭제 하시겠습니까?"
          + "(y/n)");
      if(scanner.nextLine().equalsIgnoreCase("y")){
        stmt.executeUpdate("DELETE FROM PRODUCTS"
            + " WHERE PNO = "+ index );
        System.out.println("데이터 삭제 완료");
      }
    }

  }

  private static String[] promptCommand() {
    System.out.print("명령>");
    String[] token = scanner.nextLine().split(" ");

    return token;
  }

}