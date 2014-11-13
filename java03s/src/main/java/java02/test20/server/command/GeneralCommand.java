package java02.test20.server.command;

import java.io.PrintStream;
import java.util.Map;
import java02.test20.server.MembersDao;
import java02.test20.server.annotation.Command;
import java02.test20.server.annotation.Component;

@Component
public class GeneralCommand {
  MembersDao membersDao;
  
  public void setMembersDao(MembersDao membersDao) {
    this.membersDao = membersDao;
  }
  
  
  @Command("help")
  public void help(Map<String, Object> params) throws Exception {
    PrintStream out = (PrintStream)params.get("out");
    out.println("list");
    out.println("view 아이디");
    out.println("add");
    out.println("delete 아이디");
    out.println("update 아이디");
    out.println("exit");
    out.println(); // 출력의 끝은 빈 라인을 보낸다. 
  }
}
