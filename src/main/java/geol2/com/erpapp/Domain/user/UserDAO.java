package geol2.com.erpapp.Domain.user;

import geol2.com.erpapp.Services.BCryptService;
import geol2.com.erpapp.db.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

  private BCryptService bCryptService = new BCryptService();

  private Connection conn = null;
  private PreparedStatement stmt = null;
  private ResultSet rs = null;

  private final String INSERT_USER = "INSERT INTO USERS (id, pwd, created_at) VALUES "
                      + "(?, ?, ?)";

  private final String ID_CHECK_QUERY = "SELECT * FROM USERS WHERE id = ? AND is_leave = 0";

  public boolean isAutenticated(UserVO vo) {
    boolean result = false;

    try {
      conn = JDBCUtil.getConnection();
      stmt = conn.prepareStatement(ID_CHECK_QUERY);
      stmt.setString(1, vo.getId());
      rs = stmt.executeQuery();

      while (rs.next()) {
        result = true;
        System.out.println(rs.getString(1));
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtil.close(stmt, conn);
    }

    return result;
  }

  public UserVO getOne(UserVO vo) {
    UserVO getUser = new UserVO();

    try {
      conn = JDBCUtil.getConnection();
      stmt = conn.prepareStatement(ID_CHECK_QUERY);
      stmt.setString(1, vo.getId());
      rs = stmt.executeQuery();

      while(rs.next()) {
        String userPassword = rs.getString(3);
        System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        boolean isMatch = bCryptService.matches( vo.getPwd(), userPassword);
        System.out.println(isMatch);

        if(isMatch) {
          getUser.setSeq(rs.getInt(1));
          getUser.setId(rs.getString(2));

          getUser.setCreatedAt(rs.getString(5));
          getUser.setUpdatedAt(rs.getString(6));
        }
        break;
      }

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      JDBCUtil.close(stmt, conn);
    }

    return getUser;
  }

  public boolean byOne(String id) {
    boolean result = false;
    int resultCount = 0;

    try {
      conn = JDBCUtil.getConnection();
      stmt = conn.prepareStatement(ID_CHECK_QUERY);
      stmt.setString(1, id);
      rs = stmt.executeQuery();

      while (rs.next()) {
        result = true;
        resultCount++;
        if(resultCount >= 2) {
          result = false;
          break;
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtil.close(stmt, conn);
    }

    return result;
  }

  public UserDAO insertUser(UserVO vo) {

    try {
      conn = JDBCUtil.getConnection();
      stmt = conn.prepareStatement(INSERT_USER);
      stmt.setString(1, vo.getId());
      stmt.setString(2, vo.getPwd());
      stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtil.close(stmt, conn);
    }

    return null;
  }
}
