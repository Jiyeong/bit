package java01.test53.step05;

public class ReverseIterator extends Iterator {
  int cursor;

  public ReverseIterator() {} 
  
  
  @Override
  public void setList(String[] list){ //overriding 
    /* super 키워드 : "오버라이딩 전의" 메서드를 가리킬 때 사용한다. 
        - super는 수퍼 클래스를 가리키는 것이 아니다! !주의 요망!
        (super.super.노노!!!!이런 문법은 있을 수 없음!!)
    */
    super.setList(list); // 기존 기능은 그대로 두고 
    cursor = list.length -1; // 새 명령 추가  --맨끝에 커서추가
  } 
  
  public boolean hasNext() {
    if(cursor >= 0)
      return true;
    else
      return false;
  }
  
  public String next() {
    return list[cursor--]; 
  }
}
//오버라이딩의 전형적 예제!
/*오버라이딩의 정의! 상속받은 메서드를 자신의 역할에 맞춰서 재정의*/
