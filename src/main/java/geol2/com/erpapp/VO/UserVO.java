package geol2.com.erpapp.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {

  int seq;

  String id;

  String pwd;

  int isLeave; // 0 : 회원, 1: 탈퇴

  String createdAt;

  String updatedAt;

  String deletedAt;
}
