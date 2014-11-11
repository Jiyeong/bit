package java02.test10Ex.command;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import java02.test10.Score;
import java02.test10.ProductDao;
import java02.test10.annotation.Component;
import java02.test10.annotation.Command;

@Component
public class ScoreComm {
  ProductDao scoreDao;
  Scanner scanner;

  public void setScoreDao(ProductDao scoreDao) {
    this.scoreDao = scoreDao;
  }
  public void setScanner(Scanner scanner) {
    this.scanner = scanner;
  }

  @Command("add")
  public void doAdd(Map<String, Object> params) throws Exception {

    @SuppressWarnings("unchecked")
    ArrayList<String> options = 
    (ArrayList<String>)params.get("options");

    Score score = new Score(options.get(0),
        Integer.parseInt(options.get(1)),
        Integer.parseInt(options.get(2)),
        Integer.parseInt(options.get(3)));

    scoreDao.add(score);
    System.out.println("저장하였습니다.");
  }
  @Command("list")
  public void doList(Map<String, Object> params) throws Exception {
    int index = 0;
    for (Score score : scoreDao.getList()){
      System.out.printf("%-3d %-10s %3d %3d %3d\n",
          index, score.getName(), score.getKor(),
          score.getEng(), score.getMath());
      index++;
    }
  }

  @Command("update")
  public void doUpdate(Map<String, Object> params) throws Exception {
    @SuppressWarnings("unchecked")
    ArrayList<String> options = 
    (ArrayList<String>)params.get("options");
    
    int index = Integer.parseInt(options.get(0));
    Score score = scoreDao.getData(index);
    if(score == null) {
      System.out.println("해당 인덱스의 성적 정보를 찾을 수 없습니다.");
      return;
    }

    Score tempScore = score.clone();

    String text = null;
    System.out.printf("이름(%s):", score.getName());
    text = scanner.nextLine();
    if(text.length() > 0)
      tempScore.setName(text);

    System.out.printf("국어(%d):", score.getKor());
    text = scanner.nextLine();
    if(text.length() > 0)
      tempScore.setKor(Integer.parseInt(text));

    System.out.printf("영어(%d):", score.getEng());
    text = scanner.nextLine();
    if(text.length() > 0)
      tempScore.setEng(Integer.parseInt(text));

    System.out.printf("수학(%d):", score.getMath());
    text = scanner.nextLine();
    if(text.length() > 0)
      tempScore.setMath(Integer.parseInt(text));

    System.out.println("정말 변경하시겠습니까?(y/n)");
    if(scanner.nextLine().equalsIgnoreCase("y")) {
      scoreDao.change(index, tempScore);
      System.out.println("변경하였습니다.");
    } else {
      System.out.println("변경 취소하였습니다.");
    }
  }

}
