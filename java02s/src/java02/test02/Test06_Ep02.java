/*
재귀 호출의 이해
 */
package java02.test02;

public class Test06_Ep02 {
  // Quiz : 1부터 50까지의 합을 구하라! 합계 출력.

  // 함수 호출 통해
  public static void main(String[] args) {
    System.out.println(f(50)); 
  }

  public static int f(int no) {
    
    if(no == 1) // 언제 멈출 것인가. 재귀호출에서 중요한 부분.
      return no;
    else
      return no + f(no - 1);
  }

  // 반복문 사용
  public static void main01(String[] args) {
    int sum = 0;
    for(int i = 1 ; i <=50;i++){
      System.out.println(i);
      sum += i;
    }
    System.out.println("\nsumation 1 to 50 : " + sum);
  }

  //재귀호출 ; 최소의 코드로 실행 가능. 프로그램 짜기 좋음.
  // 문제 스택에 쌓여서 오버플로 가능성 있음. 계속 반복문 돌아서 
  
}
