package java63;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardDao {
  ArrayList<Board> list = new ArrayList<Board>();
  Scanner dataScanner ;
  
  public void load(){
    try{
    dataScanner = new Scanner(new FileReader("board.dat"));
    list.add(new Board(dataScanner.nextLine()));
    }catch(Exception ex){
      System.out.println("파일작성중 에러입니당.");
    }
    
  }
  
  public void save() throws IOException{
    BufferedWriter out = null ;
    try {
      out = new BufferedWriter(new FileWriter("board.text"));
      for(Board board: list){        
        out.write(board.title + "," + board.content 
            + "," + board.password + "\n");
        System.out.println(board.title + "," + board.content 
            + "," + board.password );
      }
      
      }catch(Exception e){
        System.out.println("데이터를 못찾구 있어요...");
      }finally{
        out.close();
      }
  }
  public void add(Board board){
    list.add(board );
  }
}
