package java02.test16.command;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java02.test16.Members;
import java02.test16.MembersDao;
import java02.test16.annotationM.Command;
import java02.test16.annotationM.Component;

@Component
public class MembersCommand {
  MembersDao membersDao;
  Scanner scanner;

  public void setMembersDao(MembersDao membersDao) {
    this.membersDao = membersDao;
  }
  public void setScanner(Scanner scanner) {
    this.scanner = scanner;
  }

  @Command("add")
  public void add(Map<String, Object> params) {
    Members members = new Members();

    System.out.print("아이디:");
    members.setId(scanner.nextLine());

    System.out.printf("암호:");
    members.setPwd(scanner.nextLine());

    System.out.printf("이메일:");
    members.setEmail(scanner.nextLine());

    System.out.printf("주문자명:");
    members.setUname(scanner.nextLine());
    
    System.out.printf("전화:");
    members.setTel(scanner.nextLine());
    
    System.out.printf("팩스:");
    members.setFax(scanner.nextLine());
    
    System.out.printf("상세주소:");
    members.setDetAddr(scanner.nextLine());
    
    System.out.printf("주문자사진:");
    members.setPhot(scanner.nextLine());
    
    System.out.printf("주소번호:");
    members.setAno(Integer.parseInt(scanner.nextLine()));
    
    membersDao.insert(members);
    System.out.println("저장하였습니다.");
  }

  @Command("delete")
  public void delete(Map<String, Object> params) {
    @SuppressWarnings("unchecked")
    ArrayList<String> options = 
    (ArrayList<String>)params.get("options");

    String id = options.get(0);

    Members members = membersDao.selectOne(id);
    if (members == null) {
      System.out.println("해당 아이디의 정보를 찾을 수 없습니다.");
      return;
    }

    System.out.print(members.getId() + "을/를 삭제하시겠습니까?(y/n)");
    if (scanner.nextLine().equalsIgnoreCase("y")) {
      membersDao.delete(id);
      System.out.println("삭제하였습니다.");
    } else {
      System.out.println("삭제 취소하였습니다.");
    }
  }

  @Command("list")
  public void list(Map<String, Object> params) {
    for (Members members : membersDao.selectList()) {
      System.out.printf("%-5s %-20s %7s %-10s\n", 
          members.getId(), 
          members.getEmail(), 
          members.getUname(), 
          members.getTel());
    }
  }

  @Command("update")
  public void update(Map<String, Object> params) {
    //MembersDao membersDao = (MembersDao)params.get("membersDao");
    //Scanner scanner = (Scanner)params.get("scanner");

    @SuppressWarnings("unchecked")
    ArrayList<String> options = 
    (ArrayList<String>)params.get("options");

    String id = options.get(0);
    Members members = membersDao.selectOne(id);
    if (members == null) {
      System.out.println("해당 아이디의 정보를 찾을 수 없습니다.");
      return;
    }
    Members tempMembers = null;
    try {
      tempMembers = members.clone();
    }catch (CloneNotSupportedException ex) {
      throw new RuntimeException(ex);
    }
    String text = null;
    
    System.out.printf("암호(%s):", members.getPwd());
    text = scanner.nextLine();
    if (text.length() > 0)
      tempMembers.setPwd(text);

    System.out.printf("이메일(%s):", members.getEmail());
    text = scanner.nextLine();
    if (text.length() > 0)
      tempMembers.setEmail(text);

    System.out.printf("주문자명(%s):", members.getUname());
    text = scanner.nextLine();
    if (text.length() > 0)
      tempMembers.setUname(text);
    
    System.out.printf("전화(%s):", members.getTel());
    text = scanner.nextLine();
    if (text.length() > 0)
      tempMembers.setTel(text);
    
    System.out.printf("팩스(%s):", members.getFax());
    text = scanner.nextLine();
    if (text.length() > 0)
      tempMembers.setFax(text);
    
    System.out.printf("상세주소(%s):", members.getDetAddr());
    text = scanner.nextLine();
    if (text.length() > 0)
      tempMembers.setDetAddr(text);

    System.out.printf("주문자사진(%s):", members.getPhot());
    text = scanner.nextLine();
    if (text.length() > 0)
      tempMembers.setPhot(text);
    
    System.out.printf("주소번호(%d):", members.getAno());
    text = scanner.nextLine();
    if (text.length() > 0)
      tempMembers.setAno(Integer.parseInt(text));
    
    System.out.print("정말 변경하시겠습니까?(y/n)");
    if (scanner.nextLine().equalsIgnoreCase("y")) {
      membersDao.update(tempMembers);
      System.out.println("변경하였습니다.");
    } else {
      System.out.println("변경 취소하였습니다.");
    }
  } 


  @Command("view")
  public void view(Map<String, Object> params) throws Exception {
    //MembersDao membersDao = (MembersDao)params.get("membersDao");

    @SuppressWarnings("unchecked")
    ArrayList<String> options = 
    (ArrayList<String>)params.get("options");

    String id = options.get(0);

    Members members = membersDao.selectOne(id);
    if (members == null) {
      System.out.println("해당 아이디의 정보를 찾을 수 없습니다.");
      return;
    }

    System.out.println("아이디: " + id);
    System.out.println("이메일: " + members.getEmail());
    System.out.println("주문자명: " + members.getUname());
    System.out.println("전화: " + members.getTel());
    System.out.println("팩스: " + members.getFax());
    System.out.println("상세주소: " + members.getDetAddr());
    System.out.println("주문자사진: " + members.getPhot());
    System.out.println("주소번호: " + members.getAno());
  }
}
