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
      System.out.println("Remove-----------------------");
      System.out.println("list["+pos+"]에 값"+list[pos]+"을 Remove한다.");
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
    if (pos >= 0 && pos < this.cursor && this.cursor < list.length){ //size(), list.length보다 this.cursor!
      System.out.println("Insert-----------------------");
      for(int i = this.cursor ; i >= pos ;i--){
        if(i > pos){
          list[i] = null;
          list[i] = list[i-1];
        } else if(i == pos){
      //}
          list[pos] = value;
          System.out.println("list["+pos+"]에 값"+value+"을 Insert한다.");
          this.cursor++;
        }//if 
      }//for
      return 0;
    } else {
      return -1;
    } //if
  }
}
/*
 
if(pos >= 0 && pos < this.cursor && this.cursor < list.length) {
for(int i = this.cursor;
 
if(pos>=0 && pos<list.length)
if(pos>=this.cursor) return -1;
else{
  for(int i = this.cursor;i>=pos;i--){
  list[i] = list[i-1];
  }
}

  public int insert(int pos, String value){ //추가하기
if (pos >= 0 && pos < size()){
System.out.println("Insert-----------------------");
for(int i = this.cursor ; i >= pos ;i--){
if(i > pos){
list[i] = null;
list[i] = list[i-1];
} else if(i == pos){
list[i] = value;
System.out.println("list["+i+"]에 값"+value+"을 Insert한다.");
this.cursor++;
}//if
}//for
return 0;
} else {
return -1;
} //if
}
*/
public class CollectionTest03 {

  public static void main(String[] args) {
    MyArray2 arr = new MyArray2();
    arr.add("00000");
    arr.add("11111");
    arr.add("22222");
    arr.add("33333");
    arr.add("44444");
    arr.add("55555");
    arr.add("66666");
    arr.add("77777");

    int count = arr.size(); //초기 값 개수

    System.out.println("초기값--------------------");
    for (int i = 0; i < arr.size(); i++){
      System.out.println("list["+i+"]"+arr.get(i)); //get을 통해 주소 꺼냄
    } //for

    arr.remove(3);
    arr.remove(4);
    
    int REcount = arr.size();//제거 후 남은 개수
    int Rcount = count - arr.size(); // 제거한 개수
    if(REcount == 0) 
      System.out.println("더이상 제거할 값이 없습니다.");
    //System.out.println(arr.size());
    //System.out.println(arr.cursor);
    
    System.out.println("R----------------------------");
    for (int i = 0; i < arr.size(); i++){
      System.out.println("list["+i+"]"+arr.get(i)); //get을 통해 주소 꺼냄
    } //for

    arr.insert(-30, "-------------");
    arr.insert(10, "*************");
    arr.insert(0, "xxxxx");
    //arr.insert(3, "xxxxx");
    arr.insert(4, "xxxxx");
    arr.insert(4, "xxxxx");
    //arr.insert(5, "xxxxx");
    //arr.insert(6, "xxxxx");
    arr.insert(7, "xxxxx");
    //arr.insert(8, "xxxxx");
    
    int IEcount = arr.size(); // 제거와 추가 후 전체 개수
    int Icount = arr.size() - REcount; // 추가한 개수

    if(IEcount == 10) 
      System.out.println("더이상 추가할 공간이 없습니다.");
    
    System.out.println(count +","+ Rcount +","+ Icount+","+REcount +","+ IEcount);
    System.out.println("list[8]"+ arr.list[8]);
    System.out.println("list[9]"+ arr.list[9]);

    
    
    System.out.println("I----------------------------");
    for (int i = 0; i < arr.size(); i++){
      System.out.println("list["+i+"]"+arr.get(i)); //get을 통해 주소 꺼냄
    } //for
  }

}
