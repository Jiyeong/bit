/*
문자 데이터의 읽기
 - FileReader
 - 텍스트 파일을 문자 스트림 객체를 사용하여 읽기
 - read() : 한 문자 단위로 읽는다. => 리턴 값은 유니코드이다.
   한 문자 => 영어(1byte), 한글(2 ~ 3byte), ...
 - 문자 스트림은 읽어들인 데이터를 유니코드로 변환한다.   
 */
package java02.test02;

import java.io.FileReader;

public class Test08 {
  public static void main(String[] args) throws Exception{
    FileReader in = new FileReader("test01.txt");
    int b = -1;
    while ((b = in.read()) != -1) {
      System.out.println(Integer.toHexString(b));
    }
    in.close();
  } //영어는 1이라 똑같(00). 한글은 (0000) 3바이트 읽어서 2바이트 리턴
  // 유니코드로 바꾼다. 
  // 바이너리는 절대 FileReader로 읽음 안댐.
}
