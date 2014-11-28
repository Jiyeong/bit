package java63.web03.control;

import java.util.HashMap;
import java.util.Map;

import java63.web03.dao.ProductDao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

//"POJO" but not all pure

@Component ("/product/list.do") //컨트롤러의 아이디값 url
public class ProductListControl {
  static final int PAGE_DEFAULT_SIZE = 3;

  @Autowired
  ProductDao productDao; // dao 자동 주입. 스프링쓸거야
  
  /*
  @RequestMapping
  => 요청을 처리할 메서드를 지정하는 애노테이션
  => 즉, 이 메서드를 호출해라!라는 뜻. (요청 연결)
  */
  @RequestMapping
  public String execute(HttpServletRequest request) throws Exception {
    
    int pageNo = 0;
    int pageSize = 0;

    if(request.getParameter("pageNo") != null) {
      pageNo = Integer.parseInt(request.getParameter("pageNo"));
      pageSize = PAGE_DEFAULT_SIZE;
    }

    if(request.getParameter("pageSize") != null) {
      pageNo = Integer.parseInt(request.getParameter("pageSize"));
    }

    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", ((pageNo - 1) * pageSize));
    paramMap.put("pageSize", pageSize);
    
    request.setAttribute("products", 
        productDao.selectList(paramMap));
    
    return"/product/ProductList.jsp";
    
  }

}
