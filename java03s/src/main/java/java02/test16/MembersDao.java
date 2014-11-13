/* 

 */
package java02.test16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MembersDao {
  public MembersDao() {}


  public Members selectOne(String id) {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/studydb", 
          "study",  
          "study");

      stmt = con.createStatement();
      rs = stmt.executeQuery(
          //"SELECT UID, EMAIL, UNAME, TEL FROM MEMBERS"
          "SELECT * FROM MEMBERS"
              + " WHERE UID ='" + id + "\'");

      if (rs.next()) {
        Members members = new Members();
        members.setId(rs.getString("UID"));
        members.setPwd(rs.getString("PWD"));
        members.setEmail(rs.getString("EMAIL"));
        members.setUname(rs.getString("UNAME"));
        members.setTel(rs.getString("TEL"));
        members.setFax(rs.getString("FAX"));
        members.setDetAddr(rs.getString("DET_ADDR"));
        members.setPhot(rs.getString("PHOT"));
        members.setAno(rs.getInt("ANO"));
        return members;
      } else {
        return null;
      }
    } catch (Exception ex) {
      throw new RuntimeException(ex);

    } finally {
      try {rs.close(); } catch (Exception ex) {}
      try {stmt.close(); } catch (Exception ex) {}
      try {con.close(); } catch (Exception ex) {}
    }

  }

  public void update(Members members) {
    Connection con = null;
    Statement stmt = null;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/studydb" + 
          "?useUnicode=true&characterEncoding=utf8", 
          "study",  
          "study");

      stmt = con.createStatement();
      stmt.executeUpdate("UPDATE MEMBERS SET" 
          + " PWD='" + members.getPwd()
          + "', EMAIL='" + members.getEmail() 
          + "', UNAME='" + members.getUname()
          + "', TEL='" + members.getTel()
          + "', FAX='" + members.getFax()
          + "', DET_ADDR='" + members.getDetAddr()
          + "', PHOT='" + members.getPhot()
          + "', ANO=" + members.getAno()
          + " WHERE UID=" +"\'" +members.getId() + "\'"); 

    } catch (Exception ex) {
      throw new RuntimeException(ex);

    } finally {
      try {stmt.close(); } catch (Exception ex) {}
      try {con.close(); } catch (Exception ex) {}
    }
  }

  public void delete(String id) {
    Connection con = null;
    Statement stmt = null;

    try {
      Class.forName("com.mysql.jdbc.Driver");

      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/studydb" + 
          "?useUnicode=true&characterEncoding=utf8", 
          "study",  
          "study"); 

      stmt = con.createStatement();
      stmt.executeUpdate("DELETE FROM MEMBERS" + 
          " WHERE UID='" + id + "\'"); 

    } catch (Exception ex) {
      throw new RuntimeException(ex);

    } finally {

      try {stmt.close(); } catch (Exception ex) {}
      try {con.close(); } catch (Exception ex) {}
    }
  }

  public List<Members> selectList() {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/studydb", 
          "study",  
          "study");

      stmt = con.createStatement();
      rs = stmt.executeQuery(
          "SELECT UID,EMAIL,UNAME,TEL FROM MEMBERS");

      ArrayList<Members> list = new ArrayList<Members>();
      Members members = null;
      
      while (rs.next()) {
        members = new Members();
        members.setId(rs.getString("UID"));
        members.setEmail(rs.getString("EMAIL"));
        members.setUname(rs.getString("UNAME"));
        members.setTel(rs.getString("TEL"));
        list.add(members);
      } 
      return list;
      
    } catch (Exception ex) {
      throw new RuntimeException(ex);

    } finally {
      try {rs.close(); } catch (Exception ex) {}
      try {stmt.close(); } catch (Exception ex) {}
      try {con.close(); } catch (Exception ex) {}
    }
  }

  public void insert(Members members) {
    Connection con = null;
    Statement stmt = null;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/studydb" + 
          "?useUnicode=true&characterEncoding=utf8", 
          "study",  
          "study"); 

      stmt = con.createStatement();
      stmt.executeUpdate("INSERT INTO MEMBERS"
          + " (UID,PWD,EMAIL,UNAME,TEL,FAX,DET_ADDR,PHOT,ANO)" 
          + " VALUES('" + members.getId()
          + "', '" + members.getPwd()
          + "', '" + members.getEmail()
          + "', '" + members.getUname()
          + "', '" + members.getTel()
          + "', '" + members.getFax()
          + "', '" + members.getDetAddr()
          + "', '" + members.getPhot()
          + "', " + members.getAno() + ")"); 

    } catch (Exception ex) {
      throw new RuntimeException(ex);

    } finally {
      try {stmt.close(); } catch (Exception ex) {}
      try {con.close(); } catch (Exception ex) {}
    }

  }
  
  public static void main(String[] args) {
    MembersDao dao = new MembersDao();
    /*
    Product members = dao.selectOne(1);
    System.out.println(members);
    */
    
    /*
    List<Product> list = dao.selectList();
    for(Product p : list) {
      System.out.println(p);
    }
    */
    /*
    Product p = new Product();
    p.setNo(13);
    p.setName("LG스마트폰G3");
    p.setQuantity(10);
    p.setMakerNo(3);
    
    dao.insert(p);
    dao.update(p);
    */
    //dao.delete(13);
    
    List<Members> list = dao.selectList();
    for(Members members : list) {
      System.out.println(members);
    }
  }
}


















