// 代码生成时间: 2025-08-08 16:16:48
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class AccessControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessControlApplication.class, args);
    }

    // Inner class for Web Security Configuration
# 添加错误处理
    @EnableWebSecurity
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // Define the security configuration
            http
# 添加错误处理
                .authorizeRequests()
                    // Define the access rules for different paths
                    .antMatchers("/public/**").permitAll() // Public path, no authentication required
                    .antMatchers("/private/**").authenticated() // Private path, authentication required
                    .anyRequest().authenticated() // All other paths, authentication required
                .and()
                    .formLogin().disable() // Disable default form login
                    .httpBasic() // Enable HTTP Basic Authentication
                .and()
                    .logout() // Configure logout
                    .permitAll();
        }
# FIXME: 处理边界情况
    }
}
