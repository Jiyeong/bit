package java01.test51;

import java01.test51.MyLinkedList.Bucket;

class MyStack {
  Object[] list = new Object[100];
  int top;

  /*====================Stack Push====================*/
  public void push(Object value) {

    if(this.top != (list.length-1)){ //stack이 차있지 않으면
      this.list[this.top++] = value;
    } else
      System.out.println("Stack is Full");
    //return -1;
    // list[top++] = value;
  }


  /*====================Stack Pop====================*/
  // 마지막에 입력한 값을 꺼낸다. 목록에서 제거됨.
  public Object pop() { 

    if(!(this.top <= 0)){ //stack이 비어있으면
      return this.list[--this.top];
    } else{
      System.out.println("Stack is Empty");
      return null;
    }
  }
  // return list[--top];
}
/*====================Queue====================*/
class MyQueue {
  class Bucket {
    Object value;
    Bucket next;

  }

  Bucket start; //front
  Bucket end; //rear
  int size; //m
  
  public MyQueue(){
    start = new Bucket();
    end = start;

  }
  
  /*====================Queue Add====================*/
  public void add(Object value){
    end.value = value;  
    //Bucket empty = new Bucket();
    end.next = new Bucket();
    end = end.next;

  }
  /*====================Queue Poll====================*/
  // 첫 번째 입력 값을 꺼낸다. 목록에서 제거됨
  public Object poll() { 
    Bucket temp = new Bucket();
    
    temp.value = start.value;
    start = start.next;
   
    return temp.value;
    /*
    Object temp = start.value;
    start = start.next;
    return temp;
    */
  }
}



/*==================== M A I N ====================*/
public class CollectionTest08 {

  public static void main(String[] args) {
    MyStack stack = new MyStack();
    stack.push("0000");
    stack.push("1111");
    stack.push("2222");
    stack.push("3333");

    for(int i = 0; i < 4; i++){
      System.out.println(stack.pop());
    }//for
    /* 예상 출력 결과
      3333
      2222
      1111
      0000
     */

    System.out.println("--------------------");


    MyQueue queue = new MyQueue();
    queue.add("AAAA");
    queue.add("BBBB");
    queue.add("CCCC");
    queue.add("DDDD");
    queue.add("EEEE");

    for(int i = 0; i < 4; i++){
      System.out.println(queue.poll());
    }//for
    /* 예상 출력 결과
      AAAA
      BBBB
      CCCC
      DDDD
     */
  }

}
