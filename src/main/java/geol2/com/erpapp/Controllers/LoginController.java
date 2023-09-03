package geol2.com.erpapp.Controllers;

import geol2.com.erpapp.Domain.user.UserDAO;
import geol2.com.erpapp.Services.BCryptService;
import geol2.com.erpapp.Services.form.LoginForm;
import geol2.com.erpapp.Domain.user.UserVO;
import geol2.com.erpapp.Session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
public class LoginController {

  @Autowired
  private BCryptService bCryptService;

  @Autowired
  private SessionManager sessionManager;

  @GetMapping("/login")
  public ModelAndView getLogin(HttpServletRequest request) {
    return new ModelAndView("main/login");
  }

  @PostMapping("/login/access")
  public ResponseEntity<Object> postLogin(@ModelAttribute LoginForm LoginForm,
                                          HttpServletResponse httpServletResponse) {

    HttpHeaders headers = new HttpHeaders();

    UserVO loginVO = new UserVO();
    loginVO.setId( LoginForm.getId() );
    loginVO.setPwd( LoginForm.getPwd() );

    UserDAO dao = new UserDAO();
    UserVO getUser = dao.getOne(loginVO);
    if( getUser == null ) {
      // 없는 유저 정보
      headers.setLocation(URI.create("/error"));
      return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    // 세션 정보 삽입, 기존 세션 정보가 존재한다면 파기
    sessionManager.createSession(getUser, httpServletResponse);

    headers.setLocation(URI.create("/"));
    return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
  }

  @GetMapping("/login/success")
  public ModelAndView loginSuccess(HttpServletRequest request) {

    System.out.println("good!");
    Object sessionUser = sessionManager.getSession(request);

    return new ModelAndView("main/main");
  }

  @GetMapping("/logout")
  public ResponseEntity<Object> getLogout(HttpServletRequest request) {
    // 세션을 삭제
    sessionManager.expire(request);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(URI.create("/"));
    return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
  }
}
