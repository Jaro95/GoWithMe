package pl.coderslab.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @TODO - change antMatchers after development
     */
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/gowithme/**").permitAll()
                .antMatchers("/gowithme/app/**").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                .antMatchers("/gowithme/admin/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .and()
                .formLogin().loginPage("/gowithme/login")
                .defaultSuccessUrl("/gowithme/app", true)
                .and()
                .logout().logoutUrl("/gowithme/app/logout").logoutSuccessUrl("/gowithme/home")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/gowithme/app/logout", "GET"));
        //.and().exceptionHandling().accessDeniedPage("/403");
        return http.build();
    }
}
