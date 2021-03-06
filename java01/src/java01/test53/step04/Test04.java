/* 
4단계 
 - Iterator 변경을 자유롭게 하기 
   Test04가 사용할 Iterator를 대체하기 쉽게하자!
 - 해결책
   1) Iterator 클래스 이름을 외부에서 받자!
   2) 외부에서 받은 클래스로 Iterator 객체를 생성하자!
   3) 그 객체를 사용하여 값을 꺼내자!

 - 어떤 값을 외부에서 받는 방법
   1) 프로그램 파라미터(아규먼트)
   2) JVM 파라미터(아규먼트)
    예) java -D파라미터명=값 Test04 파라미터값 파라미터값 파라미터값 ...
     그러면 VM에게 전달된 값을 어떻게 알아내는가? 소스 보세요.
     main01 참고

 // 같은 조상이어야 한다.   
 // 업그레이드 이유 : 프로그램의 기능 확장을 쉽게 하기 위해서
 */
package java01.test53.step04;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;


public class Test04 {

  public static void main(String[] args) throws Exception {
    // System.getProperty("환경변수명")
    // => -D 옵션으로 넘어온 값 및 JVM 환경 변수 값을 꺼낸다. 
    String iteratorClassName = System.getProperty("iterator");
    // iterator 클래스 이름을 받아옴, 가져옴.
    // 프로그램 아규먼트 말고 JVM 아규먼트 있다!
    // java -D이름(:키)=값(:value)라는 뒤에 옵션을 준다.
    // -D 이름=값 -D 이름=값
    // 이걸 위해 시스템의 도움을 받아야 함. 시스템이라는 클래스(도구함)는
    // 시스템을 다루는 메서드가 잔뜩 들어가 있음.
    //그중에 겟프로퍼티라는 메서드(도구) 있음.
    // 이 도구에 이름 값을 주면 그러한 이름을 갖는 값을 리턴해줌.
    // 
    
    // 클래스 이름 (패키지명 포함)으로 객체 생성하기
    // 1) 클래스(도구함)를 로딩하라.
    Class clazz = Class.forName(iteratorClassName);//설명..!**
    //Class clazz = Class.forName("java03.test53.step04.iterator");
    // 클래스라는 이름을 갖는 클래스. 클래스의 정보를 담는 것 클래스??**
    // ()클래스를 로딩(forName)하는걸 도와주는 클래스 clazz를 만듬.??
    // 도우미 클래스 clazz. 얘만 있으면 클래스 정보, 인스턴스까지 생성 가능.젛아.
    // teratorClassName 이름을 갖는 클래스를 로딩하여, 
    // 로딩된 클래스의 정보를 클래스화

    // 2)Class 객체를 사용하여 인스턴스 생성
    Iterator iterator = (Iterator)clazz.newInstance();
    // 그 로딩된 클래스 정보를 가지고 인스턴스를 생성한다.
    // 생성된 인스턴스 주소 리턴. 주소 명확히 알려줘야해. 인스턴스 주소는 이터레이터주소야.
    // 그러니까 이터레이터 타입으로 지정해줘
    // 리턴 타입 
    // 생성한 인스턴스
    

    // 3) Iterator를 사용하기 전에 필요한 값을 설정한다.
    iterator.setList(args);
    // 별도의
    // 클래스가 결정되어있다. iterator 라고. 다른 iterator로 바꾸면 2)에서 오류남. 
    // 클래스 다름. 타입 다름. // 클래스 다르면 지정 안됨;

    while(iterator.hasNext()) {
      System.out.println(iterator.next());
    }

    System.out.println("---------------------");
    // Iterator 크래스의 메서드 이름을 출력
    Method[] methods =clazz.getMethods(); //clazz가 가리키는 곳에는 메모리가 있고.
    //그메소드이름, 정보를 추출. 하여 메서드라는 인스턴스에 담겨서 배열로 만들어진다.
    for(Method method : methods){
      System.out.println(method.getName()); //public 메서드
    }
    
    /*
    안타깝게도 다른 Iterator는 사용할 수 없다.
    다음 단계를 보기 바람!
    */
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