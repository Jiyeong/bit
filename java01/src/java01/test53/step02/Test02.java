/* 
2단계 
 - 하드 코딩되어있는 데이터 부분을 외부에서 입력 받도록 기능 개선
 */
package java01.test53.step02;

public class Test02 {

  public static void main(String[] args) {
    
    Iterator iterator = new Iterator(args); //args 하드코딩된 데이터를 이렇게 받는다. 프로그램 아규먼트로 받는다.
    // 프로그램 아규먼트로 받는다느 뜻은
    // java -Test02 (공백)값(공백)값(공백)값(엔터) -> 프로그램 아규먼트
    // 이 값을 아규먼트로 받는다.-> 소스코드 바꿀 필요 없다. 즉 데이터를 소스
    // 내에서 바꿀 필요 없다. 
    while(iterator.hasNext()) {
      System.out.println(iterator.next());
    }

  }

}

// 데이터를 임의로 바꿀 수 없음.