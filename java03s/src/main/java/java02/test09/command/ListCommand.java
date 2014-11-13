package java02.test09.command;

import java.util.Map;
import java02.test09.Score;
import java02.test09.ScoreDao;
import java02.test09.annotation.Command;
import java02.test09.annotation.Component;
//총괄 관리자로부터 의존 객체를 주입받고 싶으면, setter메서드를 준비하라.

// 이 메서드는 Test01이 호출한다.


@Component("list")
public class ListCommand{
  ScoreDao scoreDao;

  public void setScoreDao(ScoreDao scoreDao) {
    //System.out.println("List.setScoreDao() 학시리 호출됨");
    this.scoreDao = scoreDao;
  }
  public ScoreDao getScoreDao() {
    return scoreDao;
  }

  @Command
  public void doList(Map<String, Object> params) throws Exception {
    // 이 메서드가 호출되기 전에 ScoreDao 의존 객체가 저장될 것이기 때문에
    // 다음 코드는 제거한다.
    //ScoreDao scoreDao = (ScoreDao)params.get("scoreDao");
    int index = 0;
    for (Score score : scoreDao.getList()) {
      System.out.printf("%-3d %-10s %3d %3d %3d\n", 
          index, score.getName(), score.getKor(), 
          score.getEng(), score.getMath());
      index++;
    }
  }


}










