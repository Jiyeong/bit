/* 
클래스 로딩
 - 1) new 연산자를 사용하여 인스턴스를 만들 때
 - 2) Class.forName("클래스이름")을 호출하여 임의로 클래스 로딩.
 */
package java01.test45;

public class Test45 {
  public static void main(String[] args) {
    // 참조 변수를 선언할 때는 클래스가 로딩되지 않는다.
    ClassA obj1;
    
    // 클래스
    obj1 = new ClassA(); //static block 실행
    
    System.out.println(ClassA.value);
  }

}
