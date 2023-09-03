package geol2.com.erpapp.Controllers;

import geol2.com.erpapp.Domain.user.UserVO;
import geol2.com.erpapp.Services.LiveContentsServiceImpl;
import geol2.com.erpapp.Domain.live.LiveContentVO;
import geol2.com.erpapp.Session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class WelcomeController {

  @Autowired
  LiveContentsServiceImpl liveContentsService;

  @Autowired
  private SessionManager sessionManager;

  @GetMapping("/")
  public ModelAndView welcome(HttpServletRequest httpServletRequest) {

    // 세션 관리자 정보 조회
    Object session = sessionManager.getSession(httpServletRequest);

    ModelAndView mov = new ModelAndView("main/main");

    // 게시글 전체글을 가져와서 추가함
    List<LiveContentVO> liveContentList;
    liveContentList = liveContentsService.getAll();

    mov.addObject("liveContentList", liveContentList);
    mov.addObject("liveContentListCount", liveContentList.size());
    mov.addObject("sessionUser", session);
    return mov;
  }
}
