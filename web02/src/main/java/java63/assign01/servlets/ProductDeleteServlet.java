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

@WebServlet("/product/delete")
public class ProductDeleteServlet extends GenericServlet {
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
    
    productDao.delete(no);
    out.println(no + "번의 정보를 삭제하였습니다.");
    out.println();
  }

}
