/*
1 단계
 - 하드 코딩 되어 있는 데이터를 순차적으로 출력하기
 */
package java01.test53.step01;

public class Test01 {

  public static void main(String[] args) {
    String[] data = {"홍길동", "임꺽정", "유관순", "안중근", "윤봉길", "안창호"};
    
    Iterator iterator = new Iterator(data);
    while(iterator.hasNext()) {
      System.out.println(iterator.next());
    }

  }

}

// 데이터를 임의로 바꿀 수 없음.
// ver1. data가 소스에 박혀있다. => 유연하지 못하다. ==>data가 Hard coding되어있다.