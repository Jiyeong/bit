/* 
5단계 
 - Iterator 뿐만 아니라 EvenIterator, ReverseIterator도 사용할 수 있게 하기.
 - 해결책 :
   이들 클래스의 공통점을 뽑아 수퍼 클래스를 정의하라. => Generalization 수행.
   Iterator
     └-----> GeneralIterator
     └-----> EvenIterator
     └-----> RverseIterator 
   
 */
package java01.test53.step05;



public class Test05 {

  public static void main(String[] args) throws Exception {
    String iteratorClassName = System.getProperty("iterator");

    // 클래스 이름 (패키지명 포함)으로 객체 생성하기
    // 1) 클래스를 로딩하라.
    Class clazz = Class.forName(iteratorClassName);//설명..!**
    // 클래스라는 이름을 갖는 클래스. 클래스의 정보를 담는 것 클래스??**
    // ()클래스를 로딩(forName)하는걸 도와주는 클래스 clazz를 만듬.??
    // 도우미 클래스 clazz. 얘만 있으면 클래스 정보, 인스턴스까지 생성 가능.젛아.


    // 2)Class 객체를 사용하여 인스턴스 생성
    Iterator iterator = (Iterator)clazz.newInstance();
    //iterator는 추상클래스 상속받은 서브클래스.추상클래스 아님.

    // 3) Iterator를 사용하기 전에 필요한 값을 설정한다.
    iterator.setList(args);

    while(iterator.hasNext()) {
      System.out.println(iterator.next());
    }
/*
    System.out.println("---------------------");
    // Iterator 크래스의 메서드 이름을 출력
    Method[] methods =clazz.getMethods();
    for(Method method : methods){
      System.out.println(method.getName()); //public 메서드
    }
    
    
    안타깝게도 다른 Iterator는 사용할 수 없다.
    다음 단계를 보기 바람!
    
  }
  // JVM 프로퍼티 값 꺼내기 예제
  public static void main01(String[] args) {
    //-D 옵션으로 전달된 값을 꺼낼 때,
    // System.getProperty(파라미터명) 사용하라!
    System.out.println(System.getProperty("iterator"));
    System.out.println(System.getProperty("name"));
    System.out.println(System.getProperty("age"));

    System.out.println("----------------------");

    // -D 옵션으로 전달된 값을 담고 있는 객체를 왕창 꺼낸다.
    Properties props = System.getProperties();
    Enumeration keyList = props.keys();
    String key = null;
    while(keyList.hasMoreElements()) {
      key = (String)keyList.nextElement();
      System.out.println(key + " ==>" + props.getProperty(key));
    }
*/
    /*    Iterator iterator = new Iterator(args); 
    //ReverseIterator iterator = new ReverseIterator(args);


    Iterator가 바뀌더라도 다음 코드는 변경할 필요가 없다. 
    이것이 Iterator 설계 특징이다.
    즉, 꺼내는 방식에 상관없이 사용하는 쪽에서는 
    일관된 이름으로 메서드를 사용할 수 있다는 점이다.

    이렇게 꺼내는 방법을 별도의 객체로 분리함으로써 
    실행할 때 유연해진다.

    while(iterator.hasNext()) {
      System.out.println(iterator.next());
    }
     */

  }

}

// 데이터를 임의로 바꿀 수 없음.