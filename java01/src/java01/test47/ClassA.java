package java01.test47;

public class ClassA {
  static int value = 10;
  
  static{
    System.out.println("ClassA : static 블록 실행"); //A1
    ClassB.value = 100; //A2
    System.out.println("ClassA : ClassB.value = " + ClassB.value); //A3
    System.out.println("ClassA : value = " + value); //A4
  }

}
