package java01.test47;

public class ClassB {
  static int value = 10;
  
  static{
    System.out.println("ClassB => static 블록 실행");//B1
    ClassA.value = 100; //B2
    System.out.println("ClassB => ClassA.value = " + ClassA.value);//B3
    System.out.println("ClassB => value = " + value);//B4
  }

}
