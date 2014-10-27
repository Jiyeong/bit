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
 
package java02.test02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import javax.annotation.processing.FilerException;

public class Test03 {
  

    public static int ReadFnD(){

  }
  public static void main(String[] args) {

    try{ //예외가 있는  처리문 만들 때 사용

      //in = new FileReader("");
      //out = new FileWriter("");

      String source = new String();

      File file = new File(source+"test.txt");
      File dir = new File(source);

      // test.txt파일이 있는지 확인
      // 파일이 존재하면 y/n check
      if(file.isFile()){
        throw new FilerException("xxxx.xxx 파일이 이미 존재합니다. "
            + "덮어쓰시겠습니까? (y/n)");

        String YnN;
        Scanner scanner = new Scanner(System.in);
        System.out.println("xxxx.xxx 파일이 이미 존재합니다. "
            + "덮어쓰시겠습니까? (y/n)");
        YnN = scanner.nextLine();
        WriteFnD(YnN);

        // 파일이 존재하지 않으면 메시지 출력
      } else if (file.isFile() == false){
        throw new FileNotFoundException("xxxx.xxx 파일이 존재하지 않습니다.");

        // 디렉토리들이 있는지 확인
      } else if(dir.isDirectory()){
        System.out.println("xxxx.xxx는 파일이 아니라 디렉토리 입니다.");

        //디렉토리가 존재하지 않으면
      } else {
        throw new IOException("디렉토리가 없습니다.");
      }

      //FileInputStream in = null;
      //FileOutputStream out = null;



    } catch (FileNotFoundException exn) { 
      // read & write file not exist
      System.out.println(exn.getMessage());

      //red & write file exist
    } catch (FilerException exr) { 
      String YnN;
      Scanner scanner = new Scanner(System.in);
      YnN = scanner.nextLine();

      switch (YnN) {
      case "y" : 
        System.out.println("xxxx.xxx 파일을 출력하였습니다.");
        FileReader in = null;
        FileWriter out = null;

        //텍스트 파일을 읽기용으로 열어 in이라는 객체에 저장한다.
        in = new FileReader (""); 
        // 파일의 내용을 쓰기 위한 텍스트 파일의 객체 out을 만든다.
        out = new FileWriter ("");
        
        int b = 1;
        String s = new String ();
        
        // 파일에 있는 한글자씩 불러와 b에 저장. 
        // 모든 내용 저장 후 -1이 되면 반복문 종료
        while ((b = in.read()) != -1) { //()실행 완료 후 b != -1 실행.
          s = s + (char)b; // int b를 문자형 변수로 변환
          out.write(s); // s에 있는 내용 out에 쓴다. 
        }
        return ;
      case "n" : return ;
      default:
        throw new RuntimeException("recheck please");
      }

    } catch (Exception ex) { 
      System.out.println("xxxx.xxx는 파일이 아니라 디렉토리 입니다.");

    } finally {
      try { in.close(); } catch (IOException ex) {}
      try { out.close(); } catch (IOException ex) {}
    }
    //filerException
  }

}
*/