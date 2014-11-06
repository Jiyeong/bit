package java02.test03;

import java.io.Serializable;
import java.util.Scanner;

public class Test01 {
  static Scanner scanner;

  //Entity 클래스 => 사용자(개발자) 정의 데이터 타입
  static class Score implements Serializable {
    // Serializable 이 인터페이스 구현하는 이유?
    private static final long serialVersionUID = 1L;

    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float avg;

    public Score(){}

    public Score(String name, int kor, int eng, int math){
      this.name = name;
      this.kor = kor;
      this.eng = eng;
      this.math = math;
    }

  }

  public static void main(String[] args) {

    /* 1) scanner를 이용하여 명령어를 입력받는다.*/
    scanner = new Scanner(System.in);

    /*
    2) 반복문을 이용하여 입력 받는 기능을 무한 반복한다.
     - label과 break 문을 이용하여 바깥 반복문 탈출하기
    */
    loop : // while 루프에 loop이라는 라벨을 준다. loop라는 while문
      while(true){
        try {
          String[] token = promptCommand(); //**

          switch (token[0]){
          case "help": /*4) help 명령어 처리 + add 명령어 일부분 처리*/
            doHelp();

            break;
          case "add":
            doAdd(token);
            break;
          case "list":
            System.out.println("전체 목록");
            break;
          case "delete":
            System.out.println("삭제하였습니다.");
            break;
          case "update":
            System.out.println("변경하였습니다.");
            break;
          case "view":
            System.out.println("상세 정보");
            break;
          case "exit":
            System.out.println("종료하였습니다.");
            break loop; //라벨 (loop)을 이용하여 while 문을 나간다. 
          default:
            System.out.println("이 명령어를 지원하지 않습니다.");
          }
          /* 
          5)예외 상황 처리
           - 명령어 개수를 잘못 참조하는 경우 등 예외 상황 처리
          */
        } catch (Exception e){
          //e.printStackTrace(); // 예외 객체에 있는 예외 정보를 다 출력.
          // 몽땅 다 일목요연하게 정리해서 보여쥼 
          System.out.println("명령어 처리 중 오류 발생. 다시 시도해 주세요.");
        }
      }
    scanner.close(); // 스캐너 쓰면 꼭 닫아줄 것!
    /*
    catch(Error e){
    //관리자에게 메일 보낸다.
    throws e;
     */
  }
  /* 6) 각 명령어 처리 부분을 별도의 메서드로 분리 */
  private static void doHelp() {
    System.out.println("=====도움말(명령어)=====");
    System.out.println("list");
    System.out.println("view 인덱스");
    System.out.println("add 이름 국어 영어 수학");
    System.out.println("delete 인덱스");
    System.out.println("update 인덱스");
    System.out.println("exit");
    System.out.println("======================");
  }
  /* 
  7) add 명령어 처리 구현
   - 사용자가 입력한 값을 저장할 Entity 역할 클래스 정의
   - Entity : 데이터를 표현하는 역할을 수행하는 클래스
     Control : Entity와 Boundary 중계, 비지니스 로직 수행
     Boundary : 사용자에게 UI 제공하는 역할
   - Entity 클래스 => 사용자 데이터 타입(개발자가 임의적으로 
                     만든 데이터 타입)을 정의한 클래스  
   - score                  
   */
  private static void doAdd(String[] token) { //throws Exception 안써도 됨.
    Score score = new Score(
        token[1], 
        Integer.parseInt(token[2]), 
        Integer.parseInt(token[3]), 
        Integer.parseInt(token[4]));

    System.out.println("이름: " + score.name);
    System.out.println("국어: " + score.kor);
    System.out.println("영어: " + score.eng);
    System.out.println("수학: " + score.math);
    System.out.println("저장하였습니다.");
  }
  /* 
  3) 자주 사용되는 코드는 메서드 블록으로 분리한다.
   - 사용자에게서 명령어를 입력받는 부분
   - 여러 클래스 메서드에서 공통으로 사용하는 객체는 클래스 변수로 만든다.
  */
 private static String[] promptCommand() { //** 
   System.out.println("명령> "); // 명령 프롬프트 띄우기

   String[] token = scanner.nextLine().split(" ");
   return token;
   /*자주쓰는 부분 드래그 후 우클릭-> Refactor -> extract Method */
  }
}
