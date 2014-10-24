/* 
3단계 
 - 역순으로 데이터를 꺼내주는 Iterator 만들기 
   => ReverseIterator 클래스 추가
 */
// iterator 꺼내는 부분을 다각화
package java01.test53.step03;

public class Test03 {

  public static void main(String[] args) {
    
    ReverseIterator iterator = new ReverseIterator(args); 
    //EvenIterator iterator = new EvenIterator(args); 
    
    //iterator를 바꿔서 사용하려면 결국 소스 코드내에서 위의 줄을 
    // 바꿔야 실행이 되더라.
    // 그래서! iterator를 교체하더라도 소스를 바꾸지 않을 수 있는 방법은? step4
    
    /* 
    Iterator가 바뀌더라도 다음 코드는 변경할 필요가 없다. 
    이것이 Iterator 설계 특징이다.
    즉, 꺼내는 방식에 상관없이 사용하는 쪽에서는 
    일관된 이름으로 메서드를 사용할 수 있다는 점이다.
    
    이렇게 꺼내는 방법을 별도의 객체로 분리함으로써 
    실행할 때 유연해진다.
    */
    while(iterator.hasNext()) {
      System.out.println(iterator.next());
    }

  }

}

// 데이터를 임의로 바꿀 수 없음.