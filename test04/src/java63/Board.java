package java63;


import java.io.Serializable;
import java.util.Calendar;



@SuppressWarnings("serial")
public class Board implements Serializable, Cloneable  {
  String title;
  String content;
  String password;
   String date;
  
  
  public Board(){
    
  }
 
  public Board(String t, String c, String p){
    title = t;
    content = c;
    password = p;
    doDate();
  }
  
  public Board(String cvs){
    String[] token = cvs.split(",");
    this.title = token[0];
    this.content = token[1];
    this.password = token[2];
  }
  
  public void doDate(){
    Calendar cal =  Calendar.getInstance();    
    this.date = cal.get(Calendar.YEAR) + "-" +(cal.get(Calendar.MONTH) + 1)
        + "-" +cal.get(Calendar.DATE);
  }
  
  public Board clone() throws CloneNotSupportedException{
    return (Board) super.clone();
  }
}
