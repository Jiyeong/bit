package java63.web03.control;

import java.util.HashMap;
import java63.web03.dao.MemberDao;
import java63.web03.domain.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping ("/auth")
public class AuthControl {

  @Autowired MemberDao memberDao;

  @RequestMapping (value="/login", method=RequestMethod.GET)
  public String login(@CookieValue(/*required=false*/defaultValue="") 
      String uid, Model model)throws Exception {
    model.addAttribute("uid",uid);
    return "/auth/LoginForm.jsp";
  }
  
  /*
  @CookieValue
  => 요청헤더에서 쿠키 값을 꺼낸다.
  => 기본은 필수 항목이다.
  => 쿠기가 없으면 다음 오류가 뜬다.
    The request sent by the client was syntactically incorrect.
    
  required => 필수 여부 지정(기본은 true)
  defaultValue => 기본 값 지정(값이 없을 때 지정될 값)
  */

  @RequestMapping (value="/login", method=RequestMethod.POST)
  public String login(
      String uid, 
      String pwd, 
      String save,
      HttpServletResponse response,
      HttpSession session)throws Exception {
    // 달라고 하면 dispatcher servlet이 가져다 꽂아준다.

    if (save != null) { // 쿠키로 (사용자) 아이디 저장
      Cookie cookie = new Cookie("uid", uid);
      cookie.setMaxAge(60 * 60 * 24 * 15); // 60초*60분*24시간*15일 
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("uid", "");
      cookie.setMaxAge(0); // 무효화 시킴
      response.addCookie(cookie);
    }

    HashMap<String, String> params = new HashMap<>();
    params.put("userId", uid);
    params.put("password", pwd);
    Member member = memberDao.existUser(params);

    if (member != null) {
      session.setAttribute("loginUser", member);
      if (session.getAttribute("requestUrl") != null) {
        return "redirect:" +
            (String)session.getAttribute("requestUrl");
      } else {
        return "redirect:../product/list.do";
      }

    } else {
      session.invalidate(); // 세션을 제거하고 새로 만든다.
      return "redirect:login.do"; // 로그인 폼으로 보낸다.
    }
  }
  
  @RequestMapping ("/logout")
  public String execute(HttpSession session) throws Exception {
    
    session.invalidate();
    
    return "redirect:login.do"; // sendRedirect
    
  }
}  



