package geol2.com.erpapp.Services;

import geol2.com.erpapp.Domain.user.UserVO;
import java.util.List;

public interface JoinService {

  boolean isValidPassword(String pwd, String rePwd);

  void insertUser(UserVO userVO);

  void updateUser(UserVO userVO);

  List<UserVO> getAll();

  List<UserVO> byOne(int seq);

  boolean byOne(String id);

  boolean isLeave(int seq);

}
