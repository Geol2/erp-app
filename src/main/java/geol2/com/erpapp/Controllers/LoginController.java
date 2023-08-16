package geol2.com.erpapp.Controllers;

import geol2.com.erpapp.Services.form.LoginForm;
import geol2.com.erpapp.VO.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

  @GetMapping("/login")
  public ModelAndView getLogin() {
    return new ModelAndView("main/login");
  }

  @PostMapping("/login")
  public void postLogin(@ModelAttribute LoginForm form) {
    System.out.println(form);
  }
}
