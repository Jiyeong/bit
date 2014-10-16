/*
조건문 퀴즈
 - 프로그램 아규먼트로 나이를 입력 받아 청소년, 청년 등의 여부를 출력하시오
 - 참고문법 : 
   문자열을 숫자로 바꾸는 방법
    String s = "23";
    int i = Integer.parseInt(s); -> s가 숫자값으로 바뀐다.

 - java -cp ./bin java02.Test17 34(엔터)   
 */
package java01;

public class Test17 {

  public static void main(String[] args) {

    for(int i = 0; i < args.length; i++){

      int age = Integer.parseInt(args[i]); 

      if(age < 18){
        System.out.println("청소년입니다.");
      } else if (age >= 18 && age < 40) {
        System.out.println("청년입니다.");
      } else if (age >= 40 && age < 50) {
        System.out.println("장년입니다.");
      } else if (age >= 50 && age < 65) {
        System.out.println("중년입니다.");
      } else {
        System.out.println("노인입니다.");
      }
    }

  }

}
