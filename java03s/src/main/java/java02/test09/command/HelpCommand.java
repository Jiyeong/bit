package java02.test09.command;

import java.util.Map;
import java02.test09.ScoreDao;
import java02.test09.annotation.Command;
import java02.test09.annotation.Component;

@Component("help")
public class HelpCommand {
  ScoreDao scoreDao;

  public void setScoreDao(ScoreDao scoreDao) {
    this.scoreDao = scoreDao;
  }
  public ScoreDao getScoreDao() {
    return scoreDao;
  }

  @Command
  public void dohelp(Map<String, Object> params) throws Exception {
    System.out.println("list");
    System.out.println("view 인덱스");
    System.out.println("add 이름 국어 영어 수학");
    System.out.println("delete 인덱스");
    System.out.println("update 인덱스");
    System.out.println("exit");
  }
}










