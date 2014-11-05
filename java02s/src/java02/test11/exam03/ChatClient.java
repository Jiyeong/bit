/*
1대 1 채팅 클라이언트 프로그램
 */
package java02.test11.exam03;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
  static Scanner keyboard = new Scanner(System.in);///
  static String name;
  static String serverAddress;

  public static void main(String[] args) throws Exception {
    Socket socket = null;
    PrintStream out = null;
    Scanner in = null;
    
    try {
    System.out.print("이름 : ");
    name = keyboard.nextLine();

    System.out.println("서버 주소 : ");
    serverAddress = keyboard.nextLine();

    System.out.println("서버와 연결중...");

    socket = new Socket(serverAddress, 8888); //69염 32엄 142영
    //192.168.0.-

    System.out.println("서버와 연결 성공!");

    in = new Scanner(socket.getInputStream());
    out = new PrintStream(socket.getOutputStream());

    ChatReaderThread readerThread = new ChatReaderThread(in);
    readerThread.start(); //작업시작해~
    
    String message = null, line = null;

    out.println("hello" + name);
    
    while(true){ // 무한반복
      message = prompt();
      out.println(message); 
/*      line = in.nextLine(); 
      System.out.println(line);
*/
      if(message.equalsIgnoreCase("quit")) {
        //System.out.println("서버와 연결을 끊었습니다.");
        break;
      }

    }

    } catch (Exception e) {
    } finally {
      try {in.close();} catch (Exception e) {}
      try {out.close();}catch (Exception e){}
      try {socket.close();}catch (Exception e){}
      try {keyboard.close();}catch (Exception e){}
      
    }
  }

  private static String prompt() {
    System.out.println(">");
    String message = keyboard.nextLine();
    return message;
  }

}
