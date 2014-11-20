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

@WebServlet("/product/list")
public class ProductListServlet extends GenericServlet {
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
    
    
    //int pageNo = Integer.parseInt(request.getParameter("pageNo"));
    //int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    
    int pageNo = 0;
    int pageSize = 0;
    
    if (request.getParameter("pageNo") != null) {
      pageNo = Integer.parseInt((String)request.getParameter("pageNo"));
      pageSize = 3;
    }
    
    if (request.getParameter("pageSize") != null) {
      pageSize = Integer.parseInt((String)request.getParameter("pageSize"));
    }
    
    
    out.println("<html>");
    out.println("<body>");
    
    out.println("<h1> List </h1>");
    out.println("<h4>pageNo = " + pageNo + ", pageSize = " + pageSize + "</h4><br>");
    for (Product product : productDao.selectList(pageNo, pageSize)) {
    out.println("<h3>" + product.getNo() + " " 
                       + product.getName() + " " 
                       + product.getQuantity() + " "
                       + product.getMakerNo() + "</h3>" );
    }
    out.println("</body>");
    out.println("</html>");
    
  }
  

  
}
