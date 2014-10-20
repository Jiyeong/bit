package java01.test41;

public class Car {
  String maker;
  String model;
  int cc = 800; // 초기화 문장
  boolean diesel;
  java.util.Date releaseDate;
  int capacity =5; // 초기화 문장
  
// **Chc**  
/*  // Truck.java 에서 //super();가 자동 생성되는데 아래 기본 생성자가 없으면 오류난다.

   public Car() {
    this.maker = "미정";
    this.model = "미정";
  }
*/
  
  public Car(String maker, String model, int cc){
    this.maker = maker;
    this.model = model;
    this.cc = cc;
     
  }
}
