/*
Quiz
 - 예외 처리 강화
 파일을 읽을 때,
 1) 파일이 존재하지 않으면, 다음 문장 출력
     xxxx.xxx 파일이 존재하지 않습니다.
 2) 디렉토리라면
     xxxx.xxx는 파일이 아니라 디렉토리 입니다.

  파일을 쓸 때,
  1) 파일이 이미 존재한다면,
     xxxx.xxx 파일이 이미 존재합니다. 덮어쓰시겠습니까? (y/n)y
     xxxx.xxx 파일을 출력하였습니다.
  2) 파일이 존재하지 않는다면,
     xxxx.xxx 파일을 출력하였습니다.
 */

package java02.test02;

import java.io.*;
import java.util.Scanner;

public class Test03 {

  public static void main(String[] args) {

    FileInputStream in = null;
    FileOutputStream out = null;
    Scanner scanner = new Scanner(System.in);

    try{ //예외가 있는  처리문 만들 때 사용
      
      
      in = new FileInputStream(args[0]);

      int index = args[0].lastIndexOf('.');
      String newFileName = args[0].substring(0, index) + "-01"
          + args[0].substring(index);

      int b = 1;

      File file = new File(newFileName);

      if(file.isFile()){
        System.out.println(newFileName + "xxxx.xxx 파일이 이미 존재합니다. "
            + "덮어쓰시겠습니까? (y/n)");
        if(scanner.nextLine().equals("y")){
          out = new FileOutputStream(newFileName);
          while ((b = in.read()) != -1){
            out.write(b);}
          System.out.println(newFileName + "파일을 출력하였습니다.");

        }else{
          System.out.println(newFileName + "파일을 출력하지 않았습니다.");
        
        }
      } else {
        out = new FileOutputStream(newFileName);
        while ((b = in.read()) != -1){
          out.write(b);
        }
        System.out.println(newFileName + "파일을 출력하였습니다.");
      
      }
      
    } catch (FileNotFoundException ex) {
      File f = new File(args[0]);
      if(f.isDirectory() == true) {
        System.out.println(args[0] + "는 파일이 아니라 디렉토리 입니다.");
      } else {
        System.out.println(args[0] + "파일을 찾을 수 없습니다.");
      }
    }catch (IOException ex) {
      System.out.println("읽기 오류!");
      
    } finally {
      try { in.close(); } catch (IOException ex) {}
      try { out.close(); }  catch (IOException ex) {}
    }
  }
}

