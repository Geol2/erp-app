package geol2.com.erpapp.Controllers;

import geol2.com.erpapp.Domain.user.UserDAO;
import geol2.com.erpapp.Services.BCryptService;
import geol2.com.erpapp.Services.form.LoginForm;
import geol2.com.erpapp.Domain.user.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;

@RestController
public class LoginController {

  @Autowired
  private BCryptService bCryptService;

  @GetMapping("/login")
  public ModelAndView getLogin() {
    return new ModelAndView("main/login");
  }

  @PostMapping("/login/access")
  public ResponseEntity<Object> postLogin(@ModelAttribute LoginForm LoginForm, HttpServletRequest httpServletRequest) {
    HttpHeaders headers = new HttpHeaders();

    UserVO loginVO = new UserVO();
    loginVO.setId( LoginForm.getId() );
    loginVO.setPwd( LoginForm.getPwd() );

    UserDAO dao = new UserDAO();
    UserVO getUser = dao.getOne(loginVO);
    if( getUser.getCreatedAt() == null ) {
      // 없는 유저 정보
      headers.setLocation(URI.create("/error"));
      return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    // 세션 정보 삽입, 기존 세션 정보가 존재한다면 파기
    HttpSession session = httpServletRequest.getSession(true);
    session.setAttribute("seq", getUser.getSeq());
    session.setAttribute("id", getUser.getId());
    session.setAttribute("isLeave", 0);
    session.setAttribute("createdAt", getUser.getCreatedAt());


    headers.setLocation(URI.create("/"));
    return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
  }
}
