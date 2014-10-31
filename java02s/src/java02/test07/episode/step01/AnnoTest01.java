/*
애노테이션 사용
 - 애노테이션이란? 컴파일러나 JVM에게 전달하는 주석이다.
 
 1. 애노테이션 정의
   - MyAnnotation 생성

 2. 애노테이션 적용
   - MyObject 생성 및 애노테이션 적용
 3. 애노테이션 추출

 */
package java02.test07.episode.step01;

import java.lang.annotation.Annotation;

public class AnnoTest01 {
  // Class 관리자를 통해 특정 애노테이션 객체만 추출하기
  public static void main(String[] args) {
    // 클래스 로딩 방법 1 : 인스턴스를 통해 얻기
    //MyObject obj = new MyObject();
    //Class clazz = obj.getClass();
    
    // 클래스 로딩 방법 2 : Class.forName() 통해 얻기
    // Class clazz = Class.fornamee("java02.test07.epioed.MyObject");
    // 실무에선 이거 많이 씀 
    
    // 클래스 로딩 방법 3 : 클래스의 스태틱 변수를 통해 어얻기
    Class clazz = MyObject.class;
    /// 고정시켜버림
    
    // 모든 애노테이션은 java.lang.annotaion.Annotation 인터페이스를
    // 자동으로 구현한다.
    // 따라서 우리가 만든 MyAnnoration도 이 인터페이스를 구현한다.
    /*Annotation[] annos = clazz.getAnnotation();
    for(Annotation anno : annos){
      System.out.println(anno.toString());
    }*/
    
    MyAnnotation myAnno = 
        (MyAnnotation) clazz.getAnnotation(MyAnnotation.class); // 관리자
    System.out.println(myAnno.value());

  }

  
  //class객체를 얻는 또 다른 방법
  // JVM은 클래스를 로딩할 때,
  // 컴파일러는 클래스를 만들 때 "class"라는 이름의 static 변수를 자동으로 추가한다.
  // 자바의 모든 클래스는 "class"라는 static 변수를가 있다다.
  // 이 변수에는 로딩된 클래스를 다루는 "Class" 개체가 들어있다.
  public static void main02(String[] args) {
    // 클래스 이름으로 저근 가능하다.
    //MyObject obj = new MyObject();
    Class clazz = MyObject.class;
    //Class clazz = MyObject.class;
    
    // 모든 애노테이션은 java.lang.annotaion.Annotation 인터페이스를
    // 자동으로 구현한다.
    // 따라서 우리가 만든 MyAnnoration도 이 인터페이스를 구현한다.
    Annotation[] annos = clazz.getAnnotations();
    for(Annotation anno : annos){
      System.out.println(anno.toString());
    }
    
    MyAnnotation myAnno = (MyAnnotation)annos[0];
    System.out.println(myAnno.value());

  }
  
  public static void main01(String[] args) {
    MyObject obj = new MyObject();
    Class clazz = obj.getClass();
    
    // 모든 애노테이션은 java.lang.annotation.Annotation 인터페이스를
    // 자동으로 구현한다.
    // 따라서 우리가 만든 MyAnnotation도 이 인터페이스를 구현한다.
    Annotation[] annos = clazz.getAnnotations();
    for (Annotation anno : annos) {
      System.out.println(anno.toString());
    }
    
    MyAnnotation myAnno = (MyAnnotation)annos[0];
    System.out.println(myAnno.value());
  }
}
