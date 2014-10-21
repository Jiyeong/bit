package java01.test44;

public class Tank extends Vehicle{
  int range;
  boolean siegeMode;
  
  
  @Override
  public void move(int direction) {
    if(siegeMode)
      return;

    int distance = 3;
    
    switch(direction) {
    case NORTH: y-= distance; break; 
    case EAST: x+= distance; break; 
    case SOUTH: y+= distance; break; 
    case WEST: x-= distance; break; 
    }
    System.out.println("끼리릭...철컥"+ x +","+ y);
  }
}
