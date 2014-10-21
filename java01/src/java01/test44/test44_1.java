package java01.test44;

public class test44_1 {

  public static void main(String[] args) {
    //Unit u1 = new Unit();
    Tank t1 = new Tank();
    t1.move(Mover.NORTH);
    
    Vulture v1 = new Vulture();
    v1.move(Mover.NORTH);
    
    Soldier s1 = new Soldier();
    s1.move(Mover.NORTH);

  }

}
