package geol2.com.erpapp.VO;

import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LiveContentVO {
    int seq;

    String title;

    String content;

    String createdAt;

    String updatedAt;

    String deletedAt;
}
