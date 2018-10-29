package apress.spring.config;

import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration // 스프링 부트에서 구성 클래스로 쓸 클래스임을 밝힘. (XML 구성 파일이나 다름 없음)
@EnableGlobalAuthentication // 이 애너테이션을 붙인 클래스는 AuthenticationManagerBuilder의 전역 인스턴스를 구성하며 앱에 있는 모든 빈에 보안을 적용한다.
// GlobalAuthenticationConfigurerAdapter : JdbcSecurityConfiguration 클래스가 상속하는 추상 클래스로 SecurityConfigurer 인터페이스를 구현하므로 JdbcSecurityConfiguration 클래스에서 init 메서드를 재정의한다.
public class JdbcSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
  // AuthenticationManagerBuilder는 개발자가 UserDetailService와 인증 공급자를 추가해서 인증 로직을 쉽게 구현할 수 있게 도와준다.

  // DB로 보안 적용 (DB엔진은 MySQL)
  // UserDetailsService 빈 생성
  @Bean
  public UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate) {
    RowMapper<User> userRowMapper = (ResultSet rs, int i ) ->
        new User(
            rs.getString("ACCOUNT_NAME"),
            rs.getString("PASSWORD"),
            rs.getBoolean("ENABLED"),
            rs.getBoolean("ENABLED"),
            rs.getBoolean("ENABLED"),
            rs.getBoolean("ENABLED"),
            AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN"));
    return username ->  // User(UserDetailsService) 반환
        jdbcTemplate.queryForObject("SELECT * from ACCOUNT where ACCOUNT_NAME = ?",
            userRowMapper, username);
  }

  @Bean
  public static NoOpPasswordEncoder passwordEncoder() {
    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
  }

  @Autowired // 빈으로 선언된 userDetailsService 메서드가 반환한 UserDetailsService 객체를 자동 연결
  private UserDetailsService userDetailsService;

  // AuthenticationManagerBuilder 인스턴스는 UserDetailsService 인스턴스를 구성하여 인메모리, LDAP, 기타 JDBC 기반의 인증을 구현합니다.
  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.userDetailsService); // userDetailsService 구현
  }
}
