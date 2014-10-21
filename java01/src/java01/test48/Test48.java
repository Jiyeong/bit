package java01.test48;

public class Test48 {
  // hashCode() 테스트
  public static void main(String[] args) {
    Student s1 = new Student("홍길동",10);
    Student s2 = new Student("홍길동",10);
    
    System.out.println(s1.hashCode()); // 클래스명@hashvalue 에서 뒤의 해시만 출력.
    System.out.println(s2.hashCode());

  }
  
  
  // toString() 테스트
  public static void main01(String[] args) {
    Student s1 = new Student("홍길동",10); // dafault에서 재정의
    Student s2 = new Student("임꺽정",10);

    //System.out.println(s1.toString());
    //System.out.println(s2.toString());

    // println()은 인스턴스의 toString()을 호출하여 그 리턴 값을 출력한다.
    System.out.println(s1);
    System.out.println(s2);

  }

}
