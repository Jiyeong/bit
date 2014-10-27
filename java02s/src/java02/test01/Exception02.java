/*
자바의 예외 처리 문법
 - 예외 상황이 발생하면 예외 정보를 특별한 객체에 담아서 호출자에게 던진다.
   문법 => throws new Throwable("예외내용");

 - 메서드 선언부에 어떤 예외를 던지는지 지정한다.
   문법 => void 메서드명() throws Throwable{ ... }

 - 예외를 던질 수 있는 메서드는 호출하는 쪽에서는 try...catch를 사용하여
   예외 처리 코드를 준비한다.

 - 효과
    1) 예외 정보를 좀 더 "상세하게" 호출자에게 전달할 수 있다. 
    2) 작업코드와 예외처리 코드를 "분리함으로써" "코드 가독성을 높일 수 있다."
    3) 호출자에게 예외 처리를 "강제"할 수 있다. (예외처리 안할거면 메서드 쓰지마!)
 */
package java02.test01;

public class Exception02 {
  public static float divide(float a, float b) throws Throwable {
    if (b == 0)
      throw new Throwable("0으로 나누면 안돼요."); 
    //Throwable을 던질 수 있다는건 그 자식들도 다 던질수 있다는 거임.
    return a / b;
  }

  public static void main(String[] args) {
    try {
      //예외를 발생하는 코드들
      float result = divide(10,0); //(throws Throwable 없으면 에러 뜸.)
      System.out.println("결과는 : " + result);

    }catch (Throwable ex) {
      //예외가 발생했을 때 수행할 코드들
      System.out.println(ex.getMessage());
    }

  }

}
