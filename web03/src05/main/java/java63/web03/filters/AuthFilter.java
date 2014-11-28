package java63.web03.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(
      ServletRequest req, ServletResponse resp,
      FilterChain nextFilter) throws IOException, ServletException {
    
    HttpServletRequest request = (HttpServletRequest)req;
    HttpServletResponse response = (HttpServletResponse)resp;
    
    //System.out.println(request.getRequestURL());
    //System.out.println(request.getPathInfo());
    //System.out.println(request.getQueryString());
    
    if (!request.getServletPath().startsWith("/auth") && // 이 조건 없으면 무한 request 됨.
        request.getSession().getAttribute("loginUser") == null) {
    
      // 원래 클라이언트가 요청한 url을 ?표 앞뒤 경로 붙여서 오리지날 풀 경로를
      // 저장해?
      request.getSession().setAttribute("requestUrl", 
          request.getRequestURL() + "?" + request.getQueryString());
      
      response.sendRedirect(
          request.getServletContext().getContextPath() // => /web03
          + "/auth/login.do"); // this is 절대경로
          //"../auth/login.do"); // 절대 경로 넣어야지 상대경로 안돼.
      return;
      
    } else {
      nextFilter.doFilter(request, response);
    }
  }

  @Override
  public void destroy() {}

}
