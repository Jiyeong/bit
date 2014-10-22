/*
LinkedList 데이터 구조 2
 - insert()와 remove() 구현
 */
package java01.test51;



/* 버킷 관리 */
class MyLinkedList2{
  class Bucket { 
    Object value;
    Bucket next;
  }

  Bucket start;
  Bucket end;
  int size;

  //
  public MyLinkedList2(){ ///빈바구니만들기
    start = new Bucket();
    end = start;
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

  public Object get(int index){
    if(index < 0 || index >= this.size)//이 객체의 사이즈보다 작아야 한다. 
      return null; //못찾았다..

    Bucket cursor = start; // 시작주소 저장 i = 0
    for(int i = 1;i <= index; i++){
      cursor = cursor.next; // 다음 버킷 주소 저장
    }//for
    return cursor.value;
  }

  /*==========INSERT==========*/
  public int insert(int index, Object value){
    /*  // 지정된 범위 외이면, 유효하지 않는 범위라면,
        if(index < 0 || index >= this.size)
        // 유효하지 않는 인덱스는 차단한다. 버린다.
        return -1;
        Burket cursor = new Bucket();       */
    /* Bucket newNode;
    newNode = new Bucket();
    newNode.value = value;  */

    if(index < 0 || index >= this.size) //
      return -1; //유효하지 않는 인덱스 버리기

    Bucket temp = new Bucket();
    temp.value = value;

    Bucket cursor = start;

    if(index == 0){
      temp.next = start;
      start = temp;

    } else {
      for (int i = 0;i<index-1;i++){
        //for (int i = 1;i<index;i++){
        cursor = cursor.next;
      }
      temp.next = cursor.next;
      cursor.next = temp;
    }
    size++;
    return 0;
    /*if(index == 0){

      } else if( )*/
    /* //1) first in
      if(index == 0) {
        newNode = start;
        start = end;

        before.next = newNode;
        newNode.next = after;
        newNode.next = get(index+1);
        size++;
        return 0;
      } else if(index == this.size){ //2) last in
        start.next = newNode.next;
        newNode = start.next;
        size++;
        return 0;
      } else{ //3) middle


      }
     */

  }

  /*==========REMOVE==========*/
  public int remove(int index){
    if(index < 0 || index >= this.size){
      return -1;
    } // if 0보다 작거나 전체 사이즈보다 작게. 왜냐 0~7(1~8)이므로 > 아니고>=
    Bucket cursor = start;// 임시변수 커서에 스타트 값 넣어줌.

    if(index == 0){// 맨 앞임?
      start = start.next;

    } else {
      for(int i = 1; i <= index-1;i++){ // 지울 값 전의 것을 찾아야 해서 -1
        cursor = cursor.next;
      }//for

      cursor.next = cursor.next.next;
    }//if
    size--; //제거 했으니까 전체 사이즈 하나 줄어든다. (개수)
    return 0;

  }

}
//배열 : 인덱스 찾으면 바로 꺼낼수 있다. 찾는 속도 빠름. 추가 삭제가 번거롭다.
//리스트 : 순차적으로 가야해서 찾는 속도 느림.추가 삭제 쉽다.

public class CollectionTest05 {
  public static void printArray(MyLinkedList2 list) {
    for(int i = 0; i < list.size();i++){
      System.out.println(list.get(i));
    }//for
  }


  public static void main(String[] args) {
    MyLinkedList2 arr = new MyLinkedList2();
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

    System.out.println("-10번에 입력 : ---------------------------");
    arr.insert(-10, "-10에 입력");
    printArray(arr);

    System.out.println("30번에 입력 : ---------------------------");
    arr.insert(30, "30에 입력");
    printArray(arr);

    System.out.println("0번에 입력 : ---------------------------");
    arr.insert(0, "!!!!!!!");
    printArray(arr);

    System.out.println("8번에 입력 : ---------------------------");
    arr.insert(8, "@@@@@@@");
    printArray(arr);

    System.out.println("4번에 입력 : ---------------------------");
    arr.insert(4, "#######");
    printArray(arr);

    System.out.println("-30번 삭제 : ----------------------------");
    arr.remove(-30);
    printArray(arr);

    System.out.println("30번 삭제 : ----------------------------");
    arr.remove(30);
    printArray(arr);

    System.out.println("0번 삭제 : ----------------------------");
    arr.remove(0);
    printArray(arr);

    System.out.println("4번 삭제 : ----------------------------");
    arr.remove(4);
    printArray(arr);


  }

}
