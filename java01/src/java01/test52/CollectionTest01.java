/*
Collection API
 - 목록 데이터를 다루는 클래스 라이브러리
 - 모든 Collection 관련 클래스들은 Collection 규칙에 따라서 클래스를 만든다.
 - 종류
   1) List : 데이터 중복 허용
   2) Set : 데이터 중복 불가!
   3) Map : key-value 쌍으로 데이터 관리
 */
package java01.test52;

import java.util.ArrayList;
import java.util.Iterator;

/*
ArrayList 테스트
 */
public class CollectionTest01 {

  public static void main(String[] args) {
    ArrayList list = new ArrayList();
    list.add("Hello");
    list.add("Hello");
    list.add(new String("Hello"));
    list.add(10); // new Integer(10) <= autoboxing
    // 10은 int 아님? add(Object)잖아, 왜 에러안남? 
    // -> 컴파일러가 autoboxing을 통해서 Integer 객체(Object)로 바꿔줌
    
    
    // 값 꺼내기 방법1.
    /*
    for(int i = 0; i < list.size();i++){
      System.out.println(list.get(i));
    } 
    */
    
    // 값 꺼내기 방법2.
    // - for(값을 담을 변수 : 배열 또는 Collection 객체) {}
    /*
    for(Object value : list){
      System.out.println(value);
    }
    */
    
    // 값 꺼내기 방법3.
    // Iterator 객체 사용하여 꺼내기
    // - 값을 꺼낼 때 일관된 방법을 사용 => 호출하는 메서드가 같다.
    // - Iterator는 값을 꺼내는 방법(알고리즘)을 객체화 시킨 것이다.
    // 객체화? 별도의 독립된 명령어 블록으로 분리함으로써 관리하기 쉽다.
    //        다른 알고리즘으로 "대체하기" 쉽다. 
    Iterator iterator = list.iterator();
    
    while(iterator.hasNext()){
      System.out.println(iterator.next());
    }
    
    
    // iterator 장점 : 어떻게 꺼내든 .hasnext() .next() 만 있으면 꺼낼 수 있다.
    // 꺼내는 방법을 클래스화 시킴
    
    // 말 그대로 .hasnext()는 앞의 것을 처음부터 가서 다음 것에 값이 있으면
    // true로 값 반환, 없으면 false반환.
    // 말 그대로 .next()는 .hasnext() 의 다음에 들어있는 값을 반환한다.
  }

}
