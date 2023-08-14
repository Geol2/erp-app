package geol2.com.erpapp.Controllers;

import geol2.com.erpapp.Services.BCryptService;
import geol2.com.erpapp.Services.JoinServiceImpl;
import geol2.com.erpapp.VO.UserVO;

import geol2.com.erpapp.Services.form.JoinForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class JoinController {

  @Autowired
  private JoinServiceImpl joinServiceImpl;

  @Autowired
  private BCryptService bCryptService;

  @GetMapping("/join")
  public ModelAndView getJoin() {
    return new ModelAndView("join-component/join");
  }

  @PostMapping("/join")
  public ModelAndView postJoin(@ModelAttribute JoinForm joinForm) {

    UserVO vo = new UserVO();
    vo.setId( joinForm.getId() );
    vo.setPwd( bCryptService.encodeBcrypt(joinForm.getPwd(), joinForm.getPwd().length()) );
    vo.setIsLeave(0);

    if( joinServiceImpl.byOne(joinForm.getId()) ) {
      return new ModelAndView("main/error");
    }

    if( joinServiceImpl.isValidPassword(joinForm.getPwd(), joinForm.getRePwd()) ) {
      joinServiceImpl.insertUser(vo);
      return new ModelAndView("join-component/join-complete");
    }

    return new ModelAndView("main/error");
  }
}
