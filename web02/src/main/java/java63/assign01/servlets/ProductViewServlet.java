package java63.assign01.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java63.assign01.dao.ProductDao;
import java63.assign01.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/product/view")
public class ProductViewServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  ProductDao productDao;
  
  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

String resource = "java63/assign01/dao/mybatis-config.xml";
    
    InputStream inputStream = Resources.getResourceAsStream(resource);
    
    SqlSessionFactory sqlSessionFactory =
        new SqlSessionFactoryBuilder().build(inputStream); 
    
    productDao = new ProductDao(); 
    productDao.setSqlSessionFactory(sqlSessionFactory);
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    int no = Integer.parseInt((String)request.getParameter("no"));
    
    Product product = productDao.selectOne(no);
    
    if (product == null) {
      out.println("해당 번호의 제품 정보를 찾을 수 없습니다.");
      out.println();
      return;
    }
    out.println("<html>");
    out.println("<body>");
    
    out.println("<h2> < " + no + "번의 상세 정보 > </h2>");
    out.println("<h3> 제품번호: " + no + "</h3>");
    out.println("<h3> 제품명: " + product.getName() + "</h3>");
    out.println("<h3> 수량: " + product.getQuantity() + "</h3>");
    out.println("<h3> 제조사 번호: " + product.getMakerNo() + "</h3>");


    out.println("</body>");
    out.println("</html>");
    
  }
  

}
