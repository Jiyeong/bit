package java02.test07.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassFinder {
  ArrayList<String> list = new ArrayList<String>();
  String basePackage;

  public ClassFinder(String basePackage){
    this.basePackage = basePackage;
  }

  public List<String> getClassList() { //어레이리스트 라고 구체적 ㄴㄴ 추상적 왜?
    return list;
  }

  public void find(String path){
    //System.out.println(path);
    try{
      File file = new File(path);

      if (file.isFile()){
        if(file.getName().endsWith(".class")){
          String className = path.substring(6) //앞의./bin/ 빼고 출력
                                 .replace('/', '.') // /를 .으로 대체
                                 .replace('\\', '.')
                                 .replaceAll("\\.class", "");
          if(className.startsWith(basePackage)){
            list.add(className);
          }
        }
        return;

      } else if(file.isDirectory()){
        String[] filenames = file.list();

        for(String filename : filenames){
          //System.err.println(path + "/" + filename);
          find(path + "/" + filename);
        }
      }
    }catch (Exception e){

    }

  }
  /*  
    public static void main(String[] args) {
    ClassFinder f = new ClassFinder("java02.test07");
    f.find("./bin");

    List<String> classNames = f.getClassList();
    for(String className : classNames){
      System.out.println(className);
    }
  }
  */
}
