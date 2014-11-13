package java02.test09.command;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java02.test09.Score;
import java02.test09.ScoreDao;
import java02.test09.annotation.Command;
import java02.test09.annotation.Component;

import org.reflections.ReflectionUtils;


@Component("add")
public class AddCommand {
  ScoreDao scoreDao;

  public void setScoreDao(ScoreDao scoreDao) {
    this.scoreDao = scoreDao;
  }
  public ScoreDao getScoreDao() {
    return scoreDao;
  }

  // 이 메서드는 add 명령을 처리하는 기능을 수행한다.
  // 따라서 Command 애노테이션을 붙인다.
  @Command
  public void doAdd(Map<String, Object> params) throws Exception {
    //ScoreDao scoreDao = (ScoreDao)params.get("scoreDao");
    // 파라미터 타입은 반드시 Map<String, Object>

    @SuppressWarnings("unchecked")
    ArrayList<String> options = 
    (ArrayList<String>)params.get("options");

    Score score = new Score(options.get(0), 
        Integer.parseInt(options.get(1)), 
        Integer.parseInt(options.get(2)), 
        Integer.parseInt(options.get(3)));

    scoreDao.add(score);
    System.out.println("저장하였습니다.");
  }
  @Command
  public void l() {}
  public void m() {}
  @Command
  public void n() {}

  public static void main(String[] args) {
    //AddCommand obj = new AddCommand();

    // AddCommand 클래스에서 @Command 애노테이션이 붙은 메서드를 모두 찾아라.
    // withAnnotation() => 검색 조건을 담고 있는 객체를 생성하여 리턴한다.

    // getMethods(타입 정보, 조건, 조건, 조건, ...);
    // 이 타입에서 조건에 해당하는 메서드를 뽑아서 리턴한다. 


    Set<Method> methods = ReflectionUtils.getMethods(AddCommand.class); 
    // , ReflectionUtils.withAnnotation(Command.class));
    // Command.class 타입에 애노테이션이 붙은 메서드 타입?ㅁ??ㅁ
    // (메소드 찾을 타입, 조건)

    // AddCommand.class, // 메서드를 찾을 클래스 타입
    // ReflectionUtils.withAnnotation(Command.class) // 조건
    // ReflectionUtils.withReturnType(Integer.class) // 조건
    // with~ 조건 객체를 담은 객체?!?!


    for(Method m : methods) {
      System.out.println("==>" + m.getName());
    }
  }
}










