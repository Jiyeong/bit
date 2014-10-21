package java01.test44;

public class Game {
  public static void main(String[] args) {
    //Unit u1 = new unit();
    Tank t1 = new Tank();
    t1.move(0);
    
    Vulture v1= new Vulture();
    v1.move(6);
    
    Soldier s1= new Soldier();
    s1.move(3);
    
  }
}
