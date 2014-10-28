package dataList;

import java.util.ArrayList;
import java.util.Scanner;

public class Test28 {


    public static void main(String[] args) {
      System.out.println("입력");
      ArrayList list = new ArrayList();
      Scanner input = new Scanner(System.in);
      
    String str = input.nextLine();
      
    /*      list.add(str.split(" ")[0]);
      list.add(str.split(" ")[1]);
      list.add(str.split(" ")[2]);*/
      
//        list.add(input.nextLine());
      for(int i = 0; i<3; i++){
        list.add(str.split(" ")[i]);
      }
        
        System.out.println(list.get(0));
      
      
      
      for(Object a: list){
        System.out.println(a);
      }
      
      
      
    }

 

}
