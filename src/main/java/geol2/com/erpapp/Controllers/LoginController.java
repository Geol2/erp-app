package geol2.com.erpapp.Controllers;

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
  public void postLogin() {

  }
}
