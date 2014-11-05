/*
특정 명령어 블록은 스레드로 분리하기
방법 : 
1) Thread 클래스를 상속받아 만들기
2) Runnable
 */
package java02.test12;

public class Test02 {

  // 1. Thread를 상속받아 만들기
  static class MyThread extends Thread {
    // 병행으로 수행할 명령어는 run()메서드에 넣는다.
    @Override
    public void run() {
      for(int i = 0; i < 1000; i++){
        double d = 3.14; // 주춤하게 만들어 순간 당황스럽게!
        d /= 12.56; // 주춤하게 만들어 순간 당황스럽게!
        System.out.println("@@! " + i);
      }
    }
  }


  // 2. Runnable 인터페이스 구현하기   
  // 이 객체를 바로 실행할 수 없고, Thread 객체를 통해 실행시킨다.
  static class MyRunnable extends Thread {
    // 병행으로 수행할 명령어는 run()메서드에 넣는다.
    @Override
    public void run() {
      for(int i = 0; i < 1000; i++){
        double d = 3.14; 
        d /= 12.56; 
        System.out.println("> " + i);
      }
    }
  } 

  public static void main(String[] args) {
    for(int i = 0; i < 1000; i++){
      double d = 3.14; // 주춤하게 만들어 순간 당황스럽게!
      d /= 12.56; // 주춤하게 만들어 순간 당황스럽게!
      System.out.println("main : " + i);
    }

    MyThread t = new MyThread();
    t.start();

    Thread t2 = new Thread(new MyRunnable());
    t2.start();

    for(int i = 0; i < 1000; i++){
      double d = 3.14; 
      d /= 12.56; 
      System.out.println("main2 : " + i);
    }

  }

}
