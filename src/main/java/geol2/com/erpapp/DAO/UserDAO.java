package geol2.com.erpapp.DAO;

import geol2.com.erpapp.VO.UserVO;
import geol2.com.erpapp.db.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

  private Connection conn = null;
  private PreparedStatement stmt = null;
  private ResultSet rs = null;

  private final String INSERT_USER = "INSERT INTO USERS (id, pwd, created_at) VALUES "
                      + "(?, ?, ?)";

  private final String ONE_USER = "SELECT * FROM USERS WHERE id = ?";

  public boolean byOne(String id) {
    boolean result = false;

    try {
      conn = JDBCUtil.getConnection();
      stmt = conn.prepareStatement(ONE_USER);
      stmt.setString(1, id);
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
