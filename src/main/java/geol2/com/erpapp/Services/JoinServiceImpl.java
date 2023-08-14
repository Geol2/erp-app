package geol2.com.erpapp.Services;

import geol2.com.erpapp.DAO.UserDAO;
import geol2.com.erpapp.VO.UserVO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService{

  private final UserDAO userDAO;

  public JoinServiceImpl(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public boolean isValidPassword(String pwd, String rePwd) {
    boolean result = false;
    if( pwd.equals(rePwd) ) {
      result = true;
    }
    return result;
  }

  @Override
  public void insertUser(UserVO vo) {
    userDAO.insertUser(vo);
  }

  @Override
  public void updateUser(UserVO userVO) {

  }

  @Override
  public List<UserVO> getAll() {

    return null;
  }

  @Override
  public List<UserVO> byOne(int seq) {
    return null;
  }

  @Override
  public boolean byOne(String id) {
    return userDAO.byOne(id);
  }

  @Override
  public boolean isLeave(int seq) {
    return false;
  }

}
