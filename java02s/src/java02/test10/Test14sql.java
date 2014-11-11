package java02.test10;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import java02.test10.ProductDao;
import java02.test10.annotation.Command;
import java02.test10.annotation.Component;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

@SuppressWarnings ({"unchecked", "rawtypes"}) // 둘 다 경고 무시
public class Test14sql {
  static class CommandInfo {
    public Object instance;
    public Method method; // 바구니 준비
  }

  Scanner scanner; 
  ProductDao scoreDao;
  HashMap<String,CommandInfo> commandMap; // Object -> CommandInfo
  // Command -> Object

  public void init() throws Exception {
    scoreDao = new ProductDao();
    try {
      scoreDao.load();
    } catch (Exception e) {
      System.out.println("데이터 로딩 중 오류가 발생하였습니다.");
    }

    scanner = new Scanner(System.in);

    commandMap = new HashMap</*귀찮다. 컴파일러 너가 알아서 추측해서 적어라*/>();  
    //--> type 추측
    //commandMap = new HashMap<String, Object>();
    // Object -> CommandInfo
    //commandMap = new HashMap<String, CommandInfo>();
    
    Reflections reflections = new Reflections("java02.test10");
    Set<Class<?>> clazzList = reflections.getTypesAnnotatedWith(Component.class);
    //Set; 컬렉션의 서브인터페이스

    Object command = null; // Command -> Object
    Component component = null;
    Method method = null;
    CommandInfo commandInfo = null;
    Command commandAnno = null;
    
    for (Class clazz : clazzList){ // 클래스에 대해 돈다.
      component = (Component)clazz.getAnnotation(Component.class);
      command = clazz.newInstance();

      // @Component 애노테이션이 붙은 클래스에서
      // @Command 가 붙은 메서드를 모두 찾는다.
      // 그 메서드와 인스턴스를 CommandInfo에 담아서 
      // CommandMap에 등록한다.
      /*==========*/
      Set<Method> methods = ReflectionUtils.getMethods(
          clazz,/**/
          ReflectionUtils.withAnnotation(Command.class)/**/
          ); 

      for(Method m : methods) { // 메서드에 대해서 돈다.
        commandAnno = m.getAnnotation(Command.class);
        commandInfo = new CommandInfo();
        commandInfo.instance = command;
        commandInfo.method = m;
        commandMap.put(commandAnno.value(), commandInfo); //맵에넣기
        //System.out.println(command.getClass().getName() 
        //    + "=>" + m.getName());
      }
      /*==========*/

      try{
        method = clazz.getMethod("setScoreDao", ProductDao.class); 
        //System.out.println(clazz.getName() + "." + method.getName());
        method.invoke(command,scoreDao); // setScoreDao를 호출함.이해해!
      } catch (Exception e){
        // ++
      }

      // Scanner 의존 객체 주입
      try{
        method = clazz.getMethod("setScanner", Scanner.class); 
        //System.out.println(clazz.getName() + "." + method.getName());
        method.invoke(command,scanner); // setScoreDao를 호출함.이해해!
      } catch (Exception e){}

    }
  }

  public void service(){
    CommandInfo commandInfo = null;

    loop: 
      while (true) {
        try {
          String[] token = promptCommand();
          commandInfo = commandMap.get(token[0]);

          if (commandInfo == null) {
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

            /**/commandInfo.method.invoke(commandInfo.instance, params);

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
    Test02 app = new Test02();

    app.init();

    app.service();

    app.destroy();
  }

}
