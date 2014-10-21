package java01.test51;

class MyArray2{
  Object[] list = new Object[10];
  int cursor;

  public int add(Object instance){
    if(cursor < list.length) {
      list[cursor++] = instance;
      return 0;
    } else {
      return -1;
    }
  }

  public int size() {
    return cursor;
  }

  public Object get(int pos){
    return list[pos];
  }

  public int remove(int pos) { // 앞으로 땡기기
    if (pos >= 0 && pos < this.cursor){
      for(int i = pos; i < this.cursor;i++){
        if(i == (this.cursor - 1)){
          list[i] = null;
          this.cursor--;
        } else {
          list[i] = list[i + 1];
        } //if
      } //for
      return 0;
    } else {
      return -1;
    } //if
  }


  public int insert(int pos, String value){ //추가하기
    if (pos >= 0 && pos < size()){
      for(int i = this.cursor ; i >= pos ;i--){
        if(i > pos){
          list[i] = null;
          list[i] = list[i-1];
        } else if(i == pos){
          list[i] = value;
          this.cursor++;
        }//if
      }//for
    return 0;
  } else {
    return -1;
  } //if
}
}

public class CollectionTest03 {

  public static void main(String[] args) {
    MyArray2 arr = new MyArray2();
    System.out.println(arr.add("00000"));
    System.out.println(arr.add("11111"));
    System.out.println(arr.add("22222"));
    System.out.println(arr.add("33333"));
    System.out.println(arr.add("44444"));
    System.out.println(arr.add("55555"));
    System.out.println(arr.add("66666"));
    System.out.println(arr.add("77777"));


    System.out.println("--------------------");
    for (int i = 0; i < arr.size(); i++){
      System.out.println(arr.get(i)); //get을 통해 주소 꺼냄
    } //for

    arr.remove(3);

    System.out.println("--------------------");
    for (int i = 0; i < arr.size(); i++){
      System.out.println("list["+i+"]"+arr.get(i)); //get을 통해 주소 꺼냄
    } //for

    //arr.insert(3, "xxxxx");
    //arr.insert(4, "xxxxx");
    arr.insert(5, "xxxxx");
    arr.insert(6, "xxxxx");
    //arr.insert(7, "xxxxx");
    //arr.insert(9, "xxxxx");


    System.out.println("--------------------");
    for (int i = 0; i < arr.size(); i++){
      System.out.println("list["+i+"]"+arr.get(i)); //get을 통해 주소 꺼냄
    } //for
    
  }

}