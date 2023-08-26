package geol2.com.erpapp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityAppConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((requests) -> requests
      .requestMatchers("/", "/home", "/join", "*.ttf", "*.ico", "/css/*",
        "/login/*", "/error", "/logout", "/live").permitAll()
        .anyRequest()
        .authenticated()
      )
      .formLogin((form) -> form
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/")
        .failureUrl("/error?msg=login")
        .permitAll()
      )
      .logout(LogoutConfigurer::permitAll);

    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user =
      User.withDefaultPasswordEncoder()
        .username("id")
        .password("pwd")
        .roles("USER")
        .build();

    return new InMemoryUserDetailsManager(user);
  }
}
