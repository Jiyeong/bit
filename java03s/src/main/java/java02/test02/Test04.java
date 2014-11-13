/*
Quiz
 - Test04 읽어들일 파일명 출력할디렉토리명
 - 출력할 파일명이 다음과 같을 경우,
   예) Test04 img1.jpg aaa
 - aaa 폴더를 먼저 생성하고,
   그 폴더 아래에 읽어들인 파일(img1.jpg)을 출력한다.  
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

public class Test04 {

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
    /*    InputStream inStream = null;
    OutputStream outStream = null;

    try {

      File f = new File("img1.jpg");
      File fc = new File("img1-1.jpg");

      inStream = new FileInputStream(f);
      outStream = new FileOutputStream(fc);

      byte[] buffer = new byte[1024];

      int length;
      while((length = inStream.read(buffer)) > 0){
        outStream.write(buffer, 0, length);
      }

      inStream.close();
      outStream.close();

      System.out.println("file copy success");

    } catch(IOException e) {
      e.printStackTrace();
    }*/

  }

}
