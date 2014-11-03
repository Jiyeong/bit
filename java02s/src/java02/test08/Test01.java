/* 
목표1. 오픈 소스 Reflections를 사용하여 클래스 찾기
목표2. 의존 객체 자동 주입

Dependency Injection (의존 객체 주입, DI)
=> 클래스가 사용하는 의존 객체를 애플리케이션을 시작할 때 자동으로 주입하는 것.

Inversion of Control(역제어, IoC)
1) 사례 1 => 이벤트 처리
2) 사례 2 => 의존 객체 주입
ex)컴퓨터(의존객체) 는 수업(업무)에 필요한 것이지만 직접 만드는 것 아니고
센터(전체 총괄하는 매니저;test01)에서 제공(주입)

 할 일.
 1. ListCommand 클래스에 의존 객체를 주입할 수 있도록 소스 변경
    1) 의존 객체를 저장하기 위해 ScoreDao 인스턴스 변수 추가
    2) 의존 객체를 주입하기 위해 setScoreDao() 메서드를 추가한다.
 2. 나머지 Command 클래스들도 소스 변경하라!    
 3. Command 클래스를 로딩하여 객체를 생성한 후 commandMap에 등록하기 전에
    의존객체를 먼저 주입한다.

 */
package java02.test08;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java02.test08.annotation.Component;

import org.reflections.Reflections;

//test08 reflection & invoke 사용.
public class Test01 {
  Scanner scanner; 
  ScoreDao scoreDao;
  HashMap<String,Command> commandMap;

  public void init() throws Exception {
    scoreDao = new ScoreDao();
    try {
      scoreDao.load();
    } catch (Exception e) {
      System.out.println("데이터 로딩 중 오류가 발생하였습니다.");
    }

    scanner = new Scanner(System.in);

    commandMap = new HashMap<String, Command>();

    Reflections reflections = new Reflections("java02.test08");
    Set<Class<?>> clazzList = reflections.getTypesAnnotatedWith(Component.class);
    //Set; 컬렉션의 서브인터페이스
    //getTypesAnnotatedWith를 사용하기위해 reflection을 사용.

    Command command = null;
    Component component = null;
    Method method = null;


    for (Class clazz : clazzList){
      component = (Component)clazz.getAnnotation(Component.class);
      //if(component != null) { //53번줄로 인해 여기는 필요 없다.
      // 53번에서 이미 애노테이션 타입 정보만 꺼냈으므로 조건이 필요 없다.
      command = (Command)clazz.newInstance();
      // ++ 이부분 고급 기슐임..
      // 만약 setScoreDao가 있다면 호출하여 ScoreDao객체를 주입한다.
      // Class 관리자로부터 해당 클래스의 Method 객체를 얻는다.
      // invoke()를 이용하여 메서드를 호출한다.
      commandMap.put(component.value(), command); //맵에넣기

      // ScoreDao 의존 객체 주입
      try{
        method = clazz.getMethod("setScoreDao", ScoreDao.class); 
        // 메소드 이름 setScoreDao, 이 메소드의 파라미터 타입 줘야행 반드시
        // ScoreDao.class 이겅, ScoreDao는 클래스 정보 
        //System.out.println(clazz.getName() + "." + method.getName());
        // 위의 메소드 뜨면 그 메소드들이 다 호출되었다는 거임.
        method.invoke(command,scoreDao); // setScoreDao를 호출함.이해해!
        // 호출해서 값을 주입해주겠다
        //(인스턴스 주소,메서드 호출할 때 (넘겨줄 값)파라미터 주소)
        // ==> command.setScoreDao(scoreDao); 내부적으로 이것과 같다. 
        // 큰 차이점 : invoke 메소드 이름 지정할 필요 없음. 
        //아래는 명시적으로 메소드 이름 지정해야 함.
      } catch (Exception e){
        // ++
      }

      // Scanner 의존 객체 주입
      try{
        method = clazz.getMethod("setScanner", Scanner.class); 
        //System.out.println(clazz.getName() + "." + method.getName());
        method.invoke(command,scanner); // setScoreDao를 호출함.이해해!
        // setScanner
        // command
      } catch (Exception e){}

    }
  }



//}


public void service(){
  Command command = null;

  loop: 
    while (true) {
      try {
        String[] token = promptCommand();
        command = commandMap.get(token[0]);

        if (command == null) {
          System.out.println("해당 명령을 지원하지 않습니다.");
          continue;
        }

        // Command 객체에게 넘겨줄 바구니 준비
        HashMap<String,Object> params = 
            new HashMap<String,Object>();
        //params.put("scoreDao", scoreDao);
        //params.put("scanner", scanner);

        ArrayList<String> options = new ArrayList<String>();
        for (int i = 1; i < token.length; i++) {
          options.add(token[i]);
        }
        params.put("options", options);

        command.service(params);

        if (token[0].equals("exit"))
          break loop;

      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("명령어 처리 중 오류 발생. 다시 시도해 주세요.");
      }
    }
}

public void destroy(){ // 자원해제
  scanner.close();

}


private String[] promptCommand() {
  System.out.print("명령>");
  String[] token = scanner.nextLine().split(" ");
  return token;
}

public static void main(String[] args) throws Exception { 
  Test01 app = new Test01();

  app.init();

  app.service();

  app.destroy();
}

}







