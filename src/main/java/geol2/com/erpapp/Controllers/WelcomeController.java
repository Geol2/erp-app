package geol2.com.erpapp.Controllers;

import geol2.com.erpapp.Services.LiveContentsServiceImpl;
import geol2.com.erpapp.VO.LiveContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class WelcomeController {

  @Autowired
  LiveContentsServiceImpl liveContentsService;

  @GetMapping("/")
  public ModelAndView welcome() {
    ModelAndView mov = new ModelAndView("main/main");

    // 게시글 전체글을 가져와서 추가함
    List<LiveContentVO> liveContentList;
    liveContentList = liveContentsService.getAll();

    mov.addObject("liveContentList", liveContentList);
    mov.addObject("liveContentListCount", liveContentList.size());
    return mov;
  }
}
