package java01.test53.step05;

abstract public class Iterator { //일부 기능은 구현하고 일부는 서브클래스에게 ..?!?!
  String[] list;


  public void setList(String[] list){ 
    this.list = list; 
  } 

  abstract public boolean hasNext() ;
  abstract public String next();

}