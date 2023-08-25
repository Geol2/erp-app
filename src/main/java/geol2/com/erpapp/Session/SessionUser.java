package geol2.com.erpapp.Session;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@Setter
@Getter
@ToString
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class SessionUser implements Serializable {

    int id;

    String name;

    String createdAt;

    String updatedAt;

    String deletedAt;
}
