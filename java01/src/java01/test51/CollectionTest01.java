/*
Collection 다루기 1 - 배열을 사용하여 여러 객체의 주소 보관

 */
package java01.test51;

import java.util.Date;

public class CollectionTest01 {

  public static void main(String[] args) {
    String s = "Hello";
    int i = new Integer(20);
    StringBuffer sb = new StringBuffer("World");
    Date today = new Date();
    
    // 위의 네 개의 객체 주소를 저장할 배열을 선언하시오!
    Object[] arr = new String[4];
    arr[0] = s;
    arr[1] = i;
    arr[2] = sb;
    arr[3] = today;
    
    for(Object obj : arr){
      System.out.println(obj);
    }
  }

}
