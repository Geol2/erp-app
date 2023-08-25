package geol2.com.erpapp.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ErrorController {

    @GetMapping("/error")
    public ModelAndView getError() {
        ModelAndView mov =  new ModelAndView("main/error");
        return mov;
    }
}
