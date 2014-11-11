package java02.test17.server;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java02.test17.server.annotation.Command;
import java02.test17.server.annotation.Component;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

@SuppressWarnings ({"unchecked", "rawtypes"}) // 둘 다 경고 무시
public class ProductMgtServer {
  static class CommandInfo {
    public Object instance;
    public Method method; // 바구니 준비
  }

  Scanner scanner; 
  ProductDao productDao;
  HashMap<String,CommandInfo> commandMap; // Object -> CommandInfo
  // Command -> Object

  public void init() throws Exception {
    productDao = new ProductDao();
    scanner = new Scanner(System.in);
    commandMap = new HashMap<>();  

    Reflections reflections = 
        new Reflections("java02.test17.server.command");
    Set<Class<?>> clazzList = 
        reflections.getTypesAnnotatedWith(Component.class);

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
        commandMap.put(commandAnno.value(), commandInfo); 
      }
      /*==========*/

      try{
        method = clazz.getMethod("setProductDao", ProductDao.class); 
        method.invoke(command,productDao); // setProductDao를 호출함.이해해!
      } catch (Exception e){
        // ++
      }

      // Scanner 의존 객체 주입
      try{
        method = clazz.getMethod("setScanner", Scanner.class); 
        method.invoke(command,scanner); // setProductDao를 호출함.이해해!
      } catch (Exception e){}

    }
  }
  class ServiceThread extends Thread {
    Socket socket;
    Scanner in;
    PrintStream out;

    public ServiceThread(Socket socket) throws Exception {
      this.socket =socket;
      in = new Scanner(socket.getInputStream());
      out = new PrintStream(socket.getOutputStream());
    }

    @Override
    public void run() {
      CommandInfo commandInfo = null;
      try {
        String[] token = in.nextLine().split("\\?");//regular expression
        commandInfo = commandMap.get(token[0]);

        if (commandInfo == null) {
          System.out.println("해당 명령을 지원하지 않습니다.");
          out.println();
          return;
        }

        HashMap<String,Object> params = 
            new HashMap<String,Object>();

        params.put("out", out);
        
        ArrayList<String> options = new ArrayList<String>();
        for (int i = 1; i < token.length; i++) {
          options.add(token[i]);
        }
        params.put("options", options);

        commandInfo.method.invoke(commandInfo.instance, params);


      } catch (Exception e) {
        e.printStackTrace();
        out.println("명령어 처리 중 오류 발생. 다시 시도해 주세요.");
        out.println();
      } finally {
        try {in.close();} catch (Exception e) {}
        try {out.close();} catch (Exception e) {}
        try {socket.close();} catch (Exception e) {}
      }
    }
  }  


  public void service() throws Exception{
    ServerSocket serverSocket = new ServerSocket(8888);
    Socket socket = null;

    while (true) {
      socket = serverSocket.accept();
      new ServiceThread(socket).start(); //연결을 스레드에게 던져줘
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
    ProductMgtServer app = new ProductMgtServer();

    app.init();
    app.service();
    app.destroy();
  }

}







