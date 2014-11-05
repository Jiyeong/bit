package java63;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class BoardApp {
  static Scanner scanner;
  static ArrayList<Board> list = new ArrayList<Board>();
  static Board board ;
  static BoardDao boardDao;

  public static String[] promptCommand(){
    System.out.println("명령>");
    scanner = new Scanner(System.in);
    String token[] = scanner.nextLine().split(" ");

    return token;
  }

  public static void main(String[] args) throws Exception {
    loop:
      while(true) {
        String[] token = promptCommand();
        switch(token[0]){
        case "add": doAdd(); break;
        case "list": doList(); break;
        case "update": doUpdate(Integer.parseInt(token[1])); break;
        case "exit": doExit(); break loop;
        }
      }

  }

  private static void doExit() throws IOException {
    
    BufferedWriter out = null ;
    try {
      out = new BufferedWriter(new FileWriter("board.dat"));
      for(Board board: list){        
        out.write(board.title + "," + board.content 
            + "," + board.password + "\n");
        System.out.println(board.title + "," + board.content 
            + "," + board.password );
      }
      
      }catch(Exception e){
        System.out.println("데이터를 못찾구 있어요...");
      }finally{
        out.close();
      }
    
    /*try{
      boardDao = new BoardDao();
      boardDao.save();
    
    }catch(Exception ex){
      System.out.println("파일읽는중 에러났다.. 쯧ㅉ스ㅉ스...");
    }*/
  }

  private static void doUpdate(int index) throws CloneNotSupportedException {
    System.out.println("업데이트");
    board = list.get(index);
    Board tempBoard = board.clone();
    System.out.printf("제목(%s)?", board.title);
    tempBoard.title = scanner.nextLine();

    int count = 0;
    loop2:
    while(count < 2){

      System.out.print("암호 ?");
      tempBoard.password = scanner.nextLine();

      if(board.password.equals(tempBoard.password)){
        list.set(index, tempBoard);
        System.out.println("변경하였습니다.");
        break loop2;
      } else {
        count++;
        System.out.println("암호가 다릅니다.");

      }
      System.out.println("자동 취소 되었습니다.");
    }
  }

  private static void doList() {
    System.out.println("출력");

    for(int i = 0; i < list.size(); i++){
      board = list.get(i);
      System.out.printf("%3d %s %10s\n", i, board.date, board.title);
    }

  }

  private static void doAdd() {
    String title;
    String content="" ;
    String next;
    String pw;
    int i = 0;

    System.out.print("제목 :");
    title = scanner.nextLine();

    System.out.print("내용 :");
    loop3:
    while(true){
      
        next = scanner.nextLine();
        if(!next.equals("")){
          content +=next;
          i++;     
        }else{
          break loop3;
        }
      
    }
      
    System.out.print("암호 :");
    pw = scanner.nextLine();


    System.out.println("저장하시겠습니까? (y/n)");
    if(scanner.nextLine().equalsIgnoreCase("y")){
      System.out.println("저장하였습니다.");
      list.add(new Board(title, content, pw));
      System.out.println(content);
     
    } else {
      System.out.println("저장 취소하였습니다.");
    }

  }

}
