package java63.web03.servlets;

import java.io.IOException;
import java63.web03.dao.ProductDao;
import java63.web03.domain.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/*
POST 요청 처리
=> 한글이 깨지는 문제 해결
=> 처음 getParameter()를 호출하기 전에
   request.setCharactorEncoding("UTF-8") 호출하라!
   => 클라이언트가 보내는 데이터의 문자 집합을 알려줘라!
*/

@WebServlet ("/product/add.do")
public class ProductAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    RequestDispatcher rd = 
        request.getRequestDispatcher("/product/ProductForm.jsp");
    rd.forward(request, response); 
  }
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    // 다음 코드는 필터로 대체함.
    //request.setCharacterEncoding("UTF-8");
    
    Product product = new Product();
    product.setName(request.getParameter("name"));
    product.setQuantity(Integer.parseInt(request.getParameter("qty")));
    product.setMakerNo(Integer.parseInt(request.getParameter("mkno")));
    
    //AppInitServlet.productDao.insert(product);
    //ContextLoaderListener.productDao.insert(product);
    
    // ProductDao를 ServletContext 보관소에서 꺼내는 방식을 사용
    // => 단점 : 위의 방식보다 코드가 늘었다.
    // => 장점 : 특정 클래스에 종속되지 않는다. 유지보수에서 더 중요!
    //ProductDao productDao = (ProductDao)this.getServletContext()
    //                                        .getAttribute("productDao");

    //ProductDao productDao = (ProductDao)ContextLoaderListener.appCtx
    //    .getBean("productDao");
    
    
    // 스프링의 ContextLoaderListener가 준비한 
    // ApplicationContext 객체 꺼내기
    ApplicationContext appCtx = 
        WebApplicationContextUtils.getWebApplicationContext(
            this.getServletContext());
    
    ProductDao productDao = (ProductDao)appCtx.getBean("productDao");
    
    try {
    productDao.insert(product);
    
    } catch (Exception e) {
      /* 
         Forward로 다른 서블릿에게 제어권 위힘하기
          => 제어권이 넘어가면 돌아오지 않는다.
       */
      RequestDispatcher rd = 
          request.getRequestDispatcher("/common/Error.jsp");
      request.setAttribute("error", e);
      rd.forward(request, response); 
    }
    
    response.sendRedirect("list.do");
    

  }

}


