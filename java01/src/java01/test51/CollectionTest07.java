/*
Iterator 설계 비법
 - 보관소에서 데이터를 꺼낼 때 사용하는 설계 방식
   => 데이터를 꺼내주는 담당자!
 - 목적 : 
   배열에서 꺼내든, 링크드리스트에서 꺼내든, 스택에서 꺼내든
   저장소의 구조에 상관없이 동일한 인터페이스(메서드)를 사용하여
   데이터를 꺼내게 한다.
 */
package java01.test51;


/* 버킷 관리 */
class MyLinkedList4 {
  /* member inner class
     - 멤버 inner 클래스는 바깥 클래스의 인스턴스에 접근할 수 있다.
     - 메서드처런 생각하면 된다.
   */

  class Iterator {
    Bucket cursor = start;

    public boolean hasNext() {
      if(cursor != end) 
        return true;
      else 
        return false;
    }

    public Object next() {
      Object value = cursor.value;
      cursor = cursor.next;
      return value;
    }
  }

  class Bucket { 
    Object value;
    Bucket next;
  }

  Bucket start;
  Bucket end;
  int size;

  public MyLinkedList4() { ///빈바구니만들기
    start = new Bucket();
    end = start;
  }

  public Iterator iterator(){
    return new Iterator();
  }

  public int add(Object value){
    end.value = value;  
    //Bucket empty = new Bucket();
    end.next = new Bucket();
    end = end.next;
    return ++size;
  }

  public int size() {
    return size;
  }


  private Bucket getBucketByIndex(int index){ //내부적으로만 쓸거라서 private
    Bucket cursor = start; 
    for(int i = 1;i <= index; i++){
      cursor = cursor.next; 
    }//for
    return cursor;
  }

  public Object get(int index){
    if(index < 0 || index >= this.size) 
      return null; 

    //Bucket cursor = getBucketByIndex(index);
    //return cursor.value;

    return getBucketByIndex(index).value; // 리턴값에 대한 .vlaue
  }

  /*==========INSERT==========*/
  public int insert(int index, Object value){
    if(index < 0 || index >= this.size) //
      return -1; 

    Bucket temp = new Bucket();
    temp.value = value;

    if(index == 0){
      temp.next = start;
      start = temp;

    } else {
      Bucket cursor = getBucketByIndex(index-1);
      temp.next = cursor.next;
      cursor.next = temp;
    }
    size++;
    return 0;
  }

  /*==========REMOVE==========*/
  public int remove(int index){
    if(index < 0 || index >= this.size){
      return -1;
    } //if

    if(index == 0){
      start = start.next;

    } else {
      Bucket cursor = getBucketByIndex(index-1);
      cursor.next = cursor.next.next;
    }//if
    size--; 
    return 0;
  }
}

public class CollectionTest07 { // 목록에서 데이터를 전문적으로 꺼내주는!! 정의!
  public static void printArray(MyLinkedList4 list) {
    MyLinkedList4.Iterator iterator = list.iterator(); //use inner class

    while(iterator.hasNext()){ //꺼낼거 있니
      System.out.println(iterator.next());//꺼내줘
    } //while
  }

  public static void testGet(MyLinkedList4 arr){
    Object value = null;
    System.out.println("get(i)사용하기 --------------------");
    long start = System.currentTimeMillis();
    int size = arr.size();

    for (int i = 0; i < size;i++){
      value = arr.get(i);
      System.out.print(".");
    } //for
    long end = System.currentTimeMillis();
    System.out.println("\n소요시간 : " + (end - start));

  }


  public static void testIterator(MyLinkedList4 arr){
    Object value = null;      
    System.out.println("Iterator 사용하기 -----------------------");
    long start = System.currentTimeMillis();
    MyLinkedList4.Iterator iterator = arr.iterator();

    while(iterator.hasNext()){
      value = iterator.next();
      System.out.print(".");
    }//while
    long end = System.currentTimeMillis();
    System.out.println("\n소요시간 : " + (end - start));
  }




  public static void main(String[] args) {
    MyLinkedList4 arr = new MyLinkedList4();
    for(int i = 0;i < 15000;i++){
      arr.add("==>" + i);
    } // for
    testGet(arr);
    testIterator(arr);

    testGet(arr);
    testIterator(arr);
  }    

  /* Iterator 사용 테스트 */
  public static void main01(String[] args) {
    MyLinkedList4 arr = new MyLinkedList4();
    arr.add("00000 ");
    arr.add("11111 ");
    arr.add("22222 ");
    arr.add("33333 ");
    arr.add("44444 ");
    arr.add("55555 ");
    arr.add("66666 ");
    arr.add("77777 ");

    System.out.println("---------------------------");
    printArray(arr);

  }

}
