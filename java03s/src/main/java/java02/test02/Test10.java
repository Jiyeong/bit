/*
문자 데이터 출력
 - 
 */
package java02.test02;

import java.io.FileWriter;

public class Test10 {

  public static void main(String[] args) throws Exception {
    FileWriter out = new FileWriter("test10.dat");
    char[] str = {'A', 'B', 'C', '가', '각', '간'};
    for(char c : str) {
    out.write(c); 
    }
    out.close(); 
  }
// 문자 스트림의 출력
  // 스트리밍 API는 디바이스에 상관없이 동일한 이름으로 실행할 수 있다. 
  // - > Read Write
}
