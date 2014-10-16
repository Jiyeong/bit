/*
반복문
 - while (조건) 명령문;
 - while (조건) { ... }

 - do 명령문; while(조건);
 - do { ... } while(조건);

 - for (초기화명령문; 조건; 증가문) { ... }
 - for (변수: 배열이나 Collection 타입 객체) { ... }
 */
package java01;

public class Test22 {

  public static void main(String[] args) {
    // 퀴즈
    // - 1에서 100까지 출력하라. 단, 4의 배수를 출력하라!
    //   다음과 같이 출력하라!
    //   4, .....20
    //   24, 28, ...
    // - 단 20의 배수일 때 마다 다음 라인으로 가라!
    //   20의 배수이지만 60의 배수인 경우는 새 라인 앞에만 @문자를 붙여라
    
    //   4, 8, 12, 16, 20(엔터)
    //   24, 28, 32, 36, 40(엔터)
    //   44, 48, 52, 56, 60(엔터) 
    //   @64, 68, 72, 76, 80(엔터)
    //   84, 88, 92, 96, 100
    
    // -do ~ while문을 사용하라
    long startMilliSec = System.currentTimeMillis();
    int x = 1;

    do {
      if((x % 4) == 0){
        System.out.print(x+", ");
        if((x % 20) == 0){
          System.out.print("\n");
          if((x % 60) == 0) {
            System.out.print("@"); 
          }
        }
      }
      x++;
    } while (x <= 100);
    //} while (x <= 210000);
    long endMillisSec = System.currentTimeMillis();
    System.out.println(endMillisSec - startMilliSec);
    // 실행 속도 Check it!
  }

}

/*        if((x % 20) == 0){
          if((x % 60) == 0){
            System.out.print("@"+x); 
          }
          System.out.print(x);
          System.out.print("\n");
        }        */
