package java02.test10Ex.command;

import java.util.Map;
import java02.test10.ProductDao;
import java02.test10.annotation.Component;
import java02.test10.annotation.Command;

@Component
public class GeneralComm {
  ProductDao scoreDao;

  public void setScroeDao(ProductDao scroeDao) {
    this.scoreDao = scroeDao;
  }

  @Command("exit")
  public void doexit(Map<String, Object> params)throws Exception {
    try {
      scoreDao.save();
    } catch (Exception e) {
      System.out.println("데이터 저장 중 오류가 발생했습니다.");
    }
  }
}
