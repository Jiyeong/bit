/*
File을 사용하여 디렉토리 안의 파일목록을 출력한다.
 */
package java02.test02;

import java.io.File;

public class Test06 {

  public static void main(String[] args) throws Exception {
    displayList(".");
  }

  public static void displayList(String filename){
    System.out.println(filename);

    File f = new File(filename);

    if(f.isDirectory()){
      //File f = new File(".");
      //File f = new File("src");// 소스는 파일이 아니라 디렉토리
      String[] files = f.list();
      for(String name : files) { // for문으로 하위 이름들 찾아내서
        displayList(f.getPath() + "/" + name); // 출력한다.
        // 경로 + 자식 이름 (현재 위치 . 자식 bin, src, java02 ...)
      } 
      //} else {
      //  System.out.println(filename);
    }
    //System.out.println(f.getParent()+"@@");
  }
}

