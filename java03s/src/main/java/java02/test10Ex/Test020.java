/*package java02.test10Ex;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import java02.test10Ex.annotation.Command;
import java02.test10Ex.annotation.Component;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

@SuppressWarnings({"unchecked","rawtype"})
public class Test020 {
  static class CommandInfo {
    public Object instance;
    public Method method;
  }
  
  Scanner scanner;
  ScoreDao = scoreDao;
  HashMap<String, CommandInfo> CommandMap;

  public void init() throws Exception {
    scoreDao = new ScoreDao();
    try {
      scoreDao.load();
    } catch (Exception e){
      System.out.println("데이터 로딩 중 오류 발생");
    }
    
    scanner = new Scanner(System.in);
    
    commandMap = new HashMap<>();
    
    Reflections reflections = new Reflections("java02.test10Ex");;
    Set<Class<?>> clazzList = 
        reflections.getTypesAnnotatedWith(Componenet.class);
    
    Object command = null;
    Component compoenent = null;
    Method method = null;
    CommandInfo commandInfo = null;
    Command commandAnno = null;
    
    for (Class clazz : clazzList) {
      component = (Component) clazz.getAnnotation(Component.class);
      command = clazz.newInstance();
      
      Set<Method> methods = ReflectionUtils.getMethods(
          clazz, 
          ReflectionUtils.withAnnotation(Command.class));
      
      for(Method m : methods) {
        
      }
    }
  }
  public void service() {

  }
  public void destroy() {
    scanner.close();
  }
  private String[] promptCommand() {
    System.out.println("명령>");
    String token = scanner.nextLine();
    return token;
  }
  public static void main(String[] args) {
    Test020 app = new Test020();

    app.init();
    app.service();
    app.destroy();
  }

}
*/