package geol2.com.erpapp.session;

import geol2.com.erpapp.Domain.user.UserVO;
import geol2.com.erpapp.Session.SessionManager;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {

        // 세션 생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        UserVO member = new UserVO();
        sessionManager.createSession(member, response);

        // 응답 및 쿠키 생성
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies()); // 어쩌구-저쩌궁-어쩌구-저쩌구

        // 세션 조회
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        // 세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }
}
