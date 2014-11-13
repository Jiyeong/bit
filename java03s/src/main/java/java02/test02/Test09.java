/*
바이너리 데이터의 출력
 - 
 */
package java02.test02;

import java.io.FileOutputStream;

public class Test09 {

  public static void main(String[] args) throws Exception {
    FileOutputStream out = new FileOutputStream("test09.dat");
    char[] str = {'A', 'B', 'C', '가', '각', '간'};
    for(char c : str) {
    out.write(c); // 무조건 바이트 출력
    }
    out.close(); // 자원을 쓰고 닫는걸 게을리하지 말라!
  }
// 전형적인 바이너리 데이터 출력 0041 -> 41로 출력.
}
