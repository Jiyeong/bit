/*package exDataList;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Note {

  public static void main(String[] args) throws Exception {
    try {
      FileInputStream in = FileInputStream("score.dat");
      DataInputStream in2 = DataInputStream(in);

      for(int i = 0; i < in2.available();i++){
        Score inScore = new Score(in2.readUTF(), in2.readInt(),
            in2.readInt(), in2.readInt());
        Score.dataList.add(inScore);
      }

      in2.close();
      in.close();
    } catch (FileNotFoundException e){

    }finally {
      Boolean flag = null;

      while(true){

        flag = Score.menu();

        if(flag.equals(false)){
          break;
        }
      }
    }

  }
}
*/