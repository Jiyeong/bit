package exDataList;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Score {
  static ArrayList dataList = new ArrayList();
  static String[] inputList = new String[4];
  static Scanner in = new Scanner(System.in);

  String name;
  int kor;
  int eng;
  int math;

  Score(String name, int kor, int eng, int math){
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
  }

  public static boolean menu() throws Exception {
    try {
      String str = null;

      System.out.println("명령> ");
      str = in.nextLine();

      inputList = (str.split(" "));

      switch(inputList[0]){
      case "help": help(); 
        return true;
      case "list": list();
        return true;
      case "add": add(inputList[1], Integer.parseInt(inputList[2]),
          Integer.parseInt(inputList[3]),
          Integer.parseInt(inputList[4]));
        return true;
      case "delete": delete(Integer.parseInt(inputList[1]));
        return true;
      case "update": update(Integer.parseInt(inputList[1]));
        return true;
      case "view": view(Integer.parseInt(inputList[1]));
        return true;
      case "exit": exit();
        return false;
      default:
        System.out.println("잘못된 명령어 입니다.");
        return true;

      }
    } catch (ArrayIndexOutOfBoundsException e){
      System.out.println("입력 개수가 잘못되었습니다.");
      System.out.println(e.getMessage());
      return true;
    } catch (NumberFormatException e){
      System.out.println("잘못된 형식의 데이터 입니다.");
      return true;
    }
  }

  public static void help() {
    System.out.println("help");
    System.out.println("view 인덱스");
    System.out.println("add 이름 국어 영어 수학");
    System.out.println("delete 인덱스");
    System.out.println("update 인덱스");
    System.out.println("exit");
  }

  public static void list() {
    if(dataList.isEmpty()){
      System.out.println("출력할 데이터가 없습니다.");
    } else {
      for(int i = 0; i <= (dataList.size()-1);i++){
        System.out.println(i + " " + ((Score)dataList.get(i)).name
            + " " + ((Score)dataList.get(i)).kor
            + " " + ((Score)dataList.get(i)).eng
            + " " + ((Score)dataList.get(i)).math);
      }
    }
  }

  public static void add(String name, int kor, int eng, int math) {

  }

  public static void delete(int index) {
    if(dataList.isEmpty()){
      System.out.println("존재하지 않는 데이터 입니다.");
    } else if (index >= 0 && index < dataList.size()-1){
      System.out.println(((Score)dataList.get(index)).name
          + "의 성적을 삭제하시겠습니까? (y / n)");
      switch (in.nextLine()) {
      case "y": case "Y" :
        dataList.remove(index);
        System.out.println(index + "번째 데이터가 삭제되었습니다.");
        break;
      case "n": case "N" :
        System.out.println("삭제를 취소하였습니다.");
        break;
      default:
        System.out.println("유효하지 않은 입력값 입니다.");
      }
    } else {
      System.out.println("존재하지 않는 인덱스 입니다. ");
    }
  }

  public static void update(int index) {

  }

  public static void view(int index) {
    int sum = 0;
    float avg = 0.0f;

    if(index >= 0 && index < (dataList.size()-1)){
      System.out.println("출력할 데이터가 없습니다.");
    } else {
      sum = ((Score)dataList.get(index)).kor + 
          ((Score)dataList.get(index)).eng + 
          ((Score)dataList.get(index)).math;
      avg = sum / 3.0f;

      System.out.println("인덱스 : " + index);
      System.out.println("이름 : " + ((Score)dataList.get(index)).name);
      System.out.println("국어 : " + ((Score)dataList.get(index)).kor);
      System.out.println("영어 : " + ((Score)dataList.get(index)).eng);
      System.out.println("수학 : " + ((Score)dataList.get(index)).math);
      System.out.println("합계 : " + sum);
      System.out.println("평균 : " + avg);
    }
  }

  public static void exit() throws Exception {
    FileOutputStream out = new FileOutputStream("score.dat");
    DataOutputStream out2 = new DataOutputStream(out);

    for(int i = 0; i < dataList.size();i++){
      out2.writeUTF(((Score)dataList.get(i)).name);
      out2.writeInt(((Score)dataList.get(i)).kor);
      out2.writeInt(((Score)dataList.get(i)).eng);
      out2.writeInt(((Score)dataList.get(i)).math);
    }
    System.out.println("파일이 성공적으로 저장되었습니다.");

    out2.close();
    out.close();

  }

}
