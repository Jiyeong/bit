/*
File 기본 사용
 */
package java02.test02;

import java.io.File;

public class Test05 {
  public static void main(String[] args) throws Exception {
    File f = new File("../../bit/java01");

    // 결과 : /home/bit/git/bit/java02s/../../bit/java01
    System.out.println(f.getAbsolutePath()); // 그 경로 그대로 보여줌

    // 결과 : /home/bit/git/bit/java01
    System.out.println(f.getCanonicalPath()); // 계산된 실제적 경로

    // 결과 : ../../bit/java01
    System.out.println(f.getPath()); // 입력 경로 그대로

    // 결과 : java01
    System.out.println(f.getName());

    // 결과 : ../../bit
    System.out.println(f.getParent());


  }
}
