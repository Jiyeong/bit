/*
Quiz
 - 파일을 복제하는 기능을 구현하시오.
 - $ Test02 /home/bit/git/bit/java02/img1.jpg(엔터)
 - => img1.jpg 파일을 복제하여 img1-01.jpg

 - 힌트 : 출력은 FileOutputStream 클래스를 사용하라!
 */
package java02.test02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Test02 {

  public static void main(String[] args) {
    FileInputStream in = null;
    FileOutputStream out = null;

    try{
      in = new FileInputStream(args[0]);

      // arg[0]가 img1.jpg라면, => img1-01.jpg
      // 확장자 앞의 문자열을 자른다. == x
      // x + "-01" + 확장자 뒤의 문자열을 붙인다.
      /*
      String[] outs = args[0].split("\\."); //aaa.bbb.ccc.jpg
      String newFileName =  outs[outs.length-2] + "-01."
          + outs[outs.length-1];
       */
      int index = args[0].lastIndexOf('.');
      String newFileName = args[0].substring(0, index) 
          + "-01" + args[0].substring(index);
      out = new FileOutputStream(newFileName);

      int b = 1;

      while ((b = in.read()) != -1) { //()실행 완료 후 b != -1 실행.
        out.write(b);
      }

      System.out.println("file copy success");

    } catch (FileNotFoundException ex) { 
      System.out.println("img1.jpg 파일을 찾을 수 없습니다.");

    } catch (IOException ex) { 
      System.out.println("읽기 오류!");

    } finally {
      try { in.close(); } catch (IOException ex) {}
      try { out.close(); } catch (IOException ex) {}
    }

  }

}
