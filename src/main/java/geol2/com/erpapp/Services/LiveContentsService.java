package geol2.com.erpapp.Services;

import geol2.com.erpapp.Domain.live.LiveContentVO;

import java.util.List;

public interface LiveContentsService {

    List<LiveContentVO> getAll();

    List<LiveContentVO> oneById(LiveContentVO vo);

}
