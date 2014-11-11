package java02.test16.command;

import java.util.Map;
import java02.test16.MembersDao;
import java02.test16.annotationM.Command;
import java02.test16.annotationM.Component;

@Component
public class GeneralCommand {
  MembersDao membersDao;
  
  public void setScoreDao(MembersDao scoreDao) {
    this.membersDao = membersDao;
  }
  
  @Command("exit")
  public void doexit(Map<String, Object> params) throws Exception {
      System.out.println("안녕히 가세요. 뿅.");
  }
  
  @Command("help")
  public void dohelp(Map<String, Object> params) throws Exception {
    System.out.println("list");
    System.out.println("view 아이디");
    System.out.println("add");
    System.out.println("delete 아이디");
    System.out.println("update 아이디");
    System.out.println("exit");
  }
}
