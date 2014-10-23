/*
1 단계
 - 하드 코딩 되어 있는 데이터를 순차적으로 출력하기
 */
package java01.test53.step01;

public class Iterator {
  String[] list;
  int cursor;

  public Iterator(String[] list) {
    this.list = list;
  }
  
  public boolean hasNext() {
    if(cursor < list.length)
      return true;
    else
      return false;
  }
  
  public String next() {
    return list[cursor++]; // after plus cursor, to list
  }
}
