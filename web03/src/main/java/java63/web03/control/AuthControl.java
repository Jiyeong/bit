package java63.web03.control;

import java.util.HashMap;
import java63.web03.dao.MemberDao;
import java63.web03.domain.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/*
@SessionAttributes
=> Model에 저장되는 값 중에서 세션에 저장될 객체를 지정한다.
=> 사용법
    @SessionAttributes({"key","key",...})
*/

@Controller
@RequestMapping ("/auth")
// 만약 Model에 loginUser라는 이름으로 값을 저장한다면 
// 그 값은 request에 보관하지 말고 session에 보관하라.
// 그 값은 세션에 있는 값이다.
@SessionAttributes({"loginUser","requestUrl"})
public class AuthControl {

  @Autowired MemberDao memberDao;

  @RequestMapping (value="/login", method=RequestMethod.GET)
  public String login(@CookieValue(/*required=false*/defaultValue="") 
      String uid, Model model)throws Exception {
    model.addAttribute("uid",uid);
    return "auth/LoginForm";
    // return "WEB-INF/webapp/auth/LoginForm.jsp";
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
      String requestUrl, /* 세션에 저장된 값을 달라고 하려면? */
      HttpServletResponse response,
      Model model,
      SessionStatus status)throws Exception {
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
      model.addAttribute("loginUser", member);//세션에 저장
      if ((requestUrl) != null) {
        return "redirect:" + requestUrl;
      } else {
        return "redirect:../product/list.do";
      }

    } else {
      // @SessionAttributes로 지정된 값을 무효화 시킨다.
      // => 주의 !!!! 세션 전체를 무효화 시키지 않는다.
      status.setComplete();

      // 세션을 제거하고 새로 만든다. 꺼내기. 
      //세션 전체를 무효화 시킨다.
      //session.invalidate(); 
      return "redirect:login.do"; // 로그인 폼으로 보낸다.
    }
  }
  
  @RequestMapping ("/logout")
  public String execute(SessionStatus status) throws Exception {
    
    status.setComplete();
    
    return "redirect:login.do"; // sendRedirect
    
  }
}  



