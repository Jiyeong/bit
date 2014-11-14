package java02.test21.spring.exam02;

/*
스프링 설정
=> 호출할 생성자 지정하기
*/

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test01 {

  @SuppressWarnings("unused")
  public static void main(String[] args) {
    // Car c = new Car();
    ApplicationContext ctx = 
        new ClassPathXmlApplicationContext(
            new String[]{"java02/test21/spring/exam02/application-context.xml"});

    Car c1 = (Car)ctx.getBean("b01");
    Car c2 = (Car)ctx.getBean("b02");
    Car c3 = (Car)ctx.getBean("b03");
    Car c4 = (Car)ctx.getBean("b04");
    Car c5 = (Car)ctx.getBean("b05");
    //Car c6 = (Car)ctx.getBean("b06");  // 빈을 찾지 못하면 오류 발생!
    
    Car c11 = (Car)ctx.getBean("b11");
    Car c12 = (Car)ctx.getBean("b12");
    Car c13 = (Car)ctx.getBean("b13");
    
    Car c21 = (Car)ctx.getBean("b21");
    Car c22 = (Car)ctx.getBean("b22");
    Car c23 = (Car)ctx.getBean("b23");
  }

}
