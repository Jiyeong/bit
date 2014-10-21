/*
StringBuffer 클래스
=> mutable 객체 생성 => 문자열 데이터를 변경할 수 있다. 
 */
package java01.test50;

public class StringTest04 {

  public static void main(String[] args) {
    StringBuffer str1 = new StringBuffer("Hello"); //원본을 바꿈.
    StringBuffer str2 = str1.replace(2, 4, "xx");
    
    System.out.println(str1);
    System.out.println(str2);
    if (str1 == str2) System.out.println("str1 == str2");

  }

}
// 스트링 클래스는 새로운 클래스 객체를 계속 생성하므로 메모리 비효율 일어남.