package dataList;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DataList {

  public static void main(String[] args) throws Exception {

    try{
    FileInputStream in = new FileInputStream("score.dat");
    DataInputStream in2 = new DataInputStream(in);
    
    
    for(int i = 0; i < in2.available(); i++){
    System.out.println(in2.readUTF());
    System.out.println(in2.readInt());
    System.out.println(in2.readInt());
    System.out.println(in2.readInt());
    }
    
    in2.close();
    in.close();
    
    Boolean flag = null;

    while(true){

      flag = Score.menu();

      if(flag.equals(false)){
        break;
      }
      
    }
    }catch (FileNotFoundException e){
      System.out.println("파일이 존재하지 않습니다.");
    }

  }

}
