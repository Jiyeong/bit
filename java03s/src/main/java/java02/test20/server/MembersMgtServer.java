package java02.test20.server;

import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java02.test20.server.annotation.Command;
import java02.test20.server.annotation.Component;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

@SuppressWarnings ({"unchecked", "rawtypes"}) // 둘 다 경고 무시
public class MembersMgtServer {
  static class CommandInfo {
    public Object instance;
    public Method method; // 바구니 준비
  }

  Scanner scanner; 
  MembersDao membersDao;
  HashMap<String,CommandInfo> commandMap; // Object -> CommandInfo
  // Command -> Object

  public void init() throws Exception {
    // MyBatis 설정 파일 경로
    String resource = "java02/test20/server/mybatis-config.xml";
    
    // 설정 파일을 읽어 들일 입력 스트림 객체를 준비한다.
    // Resources의 getResourceAsStrean()을 사용하면,
    // mybatis 설정 파일을 클래스 경로에서 찾는 스트림 객체를 리턴한다.
    InputStream inputStream = Resources.getResourceAsStream(resource);
    // FileInputStream inputStream 
    // = new FileInputStream()
    //    "/home/bit/git/bit/java03s/bin/" + resource);
    
    
    // mybatis 설정 파일대로 동작할 sqlSessionFactory를 얻는다.
    // 빌더 역할을 수행하는 객체를 통해서 얻는다.
    SqlSessionFactory sqlSessionFactory =
        new SqlSessionFactoryBuilder().build(inputStream); //episode 참조
    
    membersDao = new MembersDao();
    scanner = new Scanner(System.in);
    commandMap = new HashMap<>();  
    
    
    membersDao.setSqlSessionFactory(sqlSessionFactory);
    
    Reflections reflections = 
        new Reflections("java02.test20.server.command");
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

      try{
        method = clazz.getMethod("setMembersDao", MembersDao.class); 
        method.invoke(command,membersDao); // setProductDao를 호출함.이해해!
      } catch (Exception e){}

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
      this.socket = socket;
      in = new Scanner(socket.getInputStream());
      out = new PrintStream(socket.getOutputStream());
    }

    private void parseQueryString(
        String query, HashMap<String,Object> map) {
      // 예) query ==>name=제품명&qty=20&mkno=6 ==> options.get(0)
      // ==> {"name=제품명", "qty=20","mkno=6"}
      String[] entryList = query.split("&");
      String[] token = null;

      for(String entry : entryList) {
        token = entry.split("="); // 예) name=제품명
        map.put(token[0], token[1]);
      }
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

        if(token.length > 1) {
          parseQueryString(token[1], params);

        }

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
    MembersMgtServer app = new MembersMgtServer();

    app.init();
    app.service();
    app.destroy();
  }

}







