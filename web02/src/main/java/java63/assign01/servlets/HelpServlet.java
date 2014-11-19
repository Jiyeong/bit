package java63.assign01.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/help")
public class HelpServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<body>");
    out.println("<h1> < Manual > </h1>");
    out.println();
    
    out.println("<h2>list</h2>");
    out.println("<p> 전체 리스트 보기 : /product/list</p>");
    out.println("<p> 리스트에서 3개 항목씩 묶음의 n번째 page 보기"
                + "/product/list?pageNo=n</p>");
    out.println("<p> 리스트에서 m개 항목씩 묶음의 n번째 page 보기"
        + "/product/list?pageNo=no&pageSize=m</p>");
    
    out.println("<h2>view 제품번호</h2>");
    out.println("<p> 선택한 번호(no)에 해당하는 상세 정보 보기"
        + " : /product/view?no=n</p>");
    
    out.println("<h2>add</h2>");
    out.println("<p> 제품의 이름, 수량, 제조사번호를 작성하여 리스트에 추가하기"
        + "/product/add?name=이름&qty=수량&mkno=제조사번호</p>");
    
    out.println("<h2>delete 제품번호</h2>");
    out.println("<p> 선택한 번호(no)에 해당하는 정보 삭제하기"
        + "/product/delete?no=n</p>");
    
    out.println("<h2>update 제품번호</h2>");
    out.println("<p> 리스트 내에서 선택한 번호의 "
        + "정보(이름, 수량, 제조사번호)를 수정한다."
        + "/product/update?no=번호&name=이름&qty=수량&mkno=제조사번호</p>");
    
    out.println("</body>");
    out.println("</html>");
    


  }
}
