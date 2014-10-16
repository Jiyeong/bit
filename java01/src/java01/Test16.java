/*
조건문
 - 명령어의 흐름을 조건에 따라 분기하는 것.
 - if (조건) ~ else ~ 
 - if (조건) {...} else {...}
 
 */
package java01;

public class Test16 {

  public static void main(String[] args) {
    int age = 20;
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
// else는 가장 가까운 if에 종속된다. if else가 하나의 문장
  }

}
