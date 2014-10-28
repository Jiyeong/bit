package ScoreTable;

import java.util.Scanner;

public class testNextLine {


  static void view(){
    System.out.println("조회합니다.");
  }


  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);


    while(true){ 
      System.out.print("명령 > ");


      String command = scanner.nextLine();
      if(command.equals("view")){
        view();
      }
    }
  }
}
