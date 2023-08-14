package geol2.com.erpapp.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class BCryptService {

  public String encodeBcrypt(String planeText, int strength) {
    return new BCryptPasswordEncoder(strength).encode(planeText);
  }

}
