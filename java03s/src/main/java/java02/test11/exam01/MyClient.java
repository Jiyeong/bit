/*
서버와 여러 번 데이터 전송
 */
package java02.test11.exam01;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {
  static Scanner keyboard = new Scanner(System.in);///

  public static void main(String[] args) throws Exception {
    System.out.println("서버와 연결중...");

    Socket socket = new Socket("192.168.0.32", 8888); //69
    System.out.println("서버와 연결 성공!");

    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());

    String message = null, line = null;
    while(true){
      message = prompt();
      out.println(message); // 서버가 데이터를 모두 읽을 때까지 리턴하지 않는다.
      line = in.nextLine(); // 서버가 문자열 한 줄을 보낼때 까지 리턴안함.
      System.out.println(line);

      if(line.equalsIgnoreCase("Goodbye")) {
        System.out.println("서버와 연결을 끊었습니다.");
        break;
      }

    }

    in.close();
    out.close();
    socket.close();
    keyboard.close();///
  }

  private static String prompt() {
    System.out.println(">");
    String message = keyboard.nextLine();
    return message;
  }

}
