package geol2.com.erpapp.Services;

import geol2.com.erpapp.Domain.live.LiveContentDAO;
import geol2.com.erpapp.Domain.live.LiveContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveContentsServiceImpl implements LiveContentsService{

    @Autowired
    private LiveContentDAO liveContentDAO;

    public LiveContentsServiceImpl(LiveContentDAO liveContentDAO) {
        this.liveContentDAO = liveContentDAO;
    }

    @Override
    public List<LiveContentVO> getAll() {
        this.liveContentDAO = new LiveContentDAO();
        return liveContentDAO.getAll();
    }

    @Override
    public List<LiveContentVO> oneById(LiveContentVO vo) {
        return null;
    }
}
