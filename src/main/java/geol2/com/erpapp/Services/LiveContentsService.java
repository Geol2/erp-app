package geol2.com.erpapp.Services;

import geol2.com.erpapp.VO.LiveContentVO;

import java.util.List;

public interface LiveContentsService {

    List<LiveContentVO> getAll();

    List<LiveContentVO> oneById(LiveContentVO vo);

}
