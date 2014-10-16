/*
조건문 퀴즈
 - 다음과 같이 동작하도록 프로그램을 작성하시오!
 - java java01.Test18
   당신의 나이가 어떻게 되는지 다음 보기에서 고르시오!
   1) 10대
   2) 20대
   3) 30대
   4) 40대
   5) 50대
   6) 기타
   번호? 3
   당신은 30대 입니다.

 - 사용자로부터 키보드 입력 값을 받는 방법!
   Scanner scanner = new Scanner(System.in);
   String input = scanner.nextline();
 - 조건 : 반드시 switch문을 사용하라!  
 */
package java01;

public class Test18 {

  public static void main(String[] args) {
    System.out.println("당신의 나이가 어떻게 되는지 다음 보기에서 고르시오!\n");
    System.out.println("------------------------------");
    System.out.println("1) 10대\n2) 20대\n3) 30대\n4) 40대\n5) 50대\n6) 기타\n");
    System.out.println("------------------------------");
    System.out.println("번호?");

    java.util.Scanner scanner = new java.util.Scanner(System.in);
    int input = Integer.parseInt(scanner.nextLine());

    switch(input){
    case 1 : System.out.println("당신은 10대 입니다."); break;
    case 2 : System.out.println("당신은 20대 입니다."); break;
    case 3 : System.out.println("당신은 30대 입니다."); break;
    case 4 : System.out.println("당신은 40대 입니다."); break;
    case 5 : System.out.println("당신은 50대 입니다."); break;  
    default : System.out.println("당신은 기타 입니다."); break;
    }
  }

}
