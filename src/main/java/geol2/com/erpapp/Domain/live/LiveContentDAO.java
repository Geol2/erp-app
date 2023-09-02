package geol2.com.erpapp.Domain.live;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LiveContentDAO {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    private final String GET_ALL = "SELECT * FROM LIVE_CONTENTS WHERE deleted_at IS NULL";

    public List<LiveContentVO> getAll() {

        List<LiveContentVO> liveContentList = new ArrayList<>();

        try {
            conn = geol2.com.erpapp.db.JDBCUtil.getConnection();
            stmt = conn.prepareStatement(GET_ALL);
            rs = stmt.executeQuery();

            while(rs.next()) {
                LiveContentVO vo = new LiveContentVO();
                vo.setSeq(rs.getInt(1));
                vo.setTitle(rs.getString(2));
                vo.setContent(rs.getString(3));
                vo.setCreatedAt(rs.getString(4));

                liveContentList.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            geol2.com.erpapp.db.JDBCUtil.close(stmt, conn);
        }

        return liveContentList;
    }
}
