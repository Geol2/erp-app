package geol2.com.erpapp.Controllers;

import geol2.com.erpapp.Services.LiveContentsServiceImpl;
import geol2.com.erpapp.Domain.live.LiveContentVO;
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

  @GetMapping("/")
  public ModelAndView welcome(@SessionAttribute(name="id", required = false) String id,
                              @SessionAttribute(name="isLeave", required = false) String isLeave,
                              @SessionAttribute(name="createdAt", required = false) String createdAt,
                              @SessionAttribute(name="updatedAt", required = false) String updatedAt) {
    ModelAndView mov = new ModelAndView("main/main");

    // 게시글 전체글을 가져와서 추가함
    List<LiveContentVO> liveContentList;
    liveContentList = liveContentsService.getAll();

    mov.addObject("liveContentList", liveContentList);
    mov.addObject("liveContentListCount", liveContentList.size());
    mov.addObject("sessionId", id);
    mov.addObject("sessionIsLeave", isLeave);
    mov.addObject("sessionCreatedAt", createdAt);
    mov.addObject("sessionUpdatedAt", updatedAt);
    return mov;
  }
}
