package uz.elmurodov.spring_security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import java.util.concurrent.TimeUnit;

import static uz.elmurodov.spring_security.enums.Role.*;
import static uz.elmurodov.spring_security.enums.Role.MANAGER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    private final String[] WHITE_LIST = {
            "/", "/css/**"
    };


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfigurer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .antMatchers(WHITE_LIST)
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .formLogin(httpSecurityFormLoginConfigurer ->
                        httpSecurityFormLoginConfigurer
                                .permitAll()
                                .loginPage("/auth/login")
                                .loginProcessingUrl("/auth/login")
                                .usernameParameter("uname")
                                .passwordParameter("pswd")
                                .defaultSuccessUrl("/", true))
                .rememberMe(httpSecurityRememberMeConfigurer ->
                        httpSecurityRememberMeConfigurer
                                .rememberMeParameter("remember-me")
                                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(40))
                                .key("DFGFGD#$%T#$@T@#$%%^$%^$RGHEFGH$%^Y&^$&#%^#$%@W#ER%TYBDFGH"))
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer
                                .logoutUrl("/auth/logout")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID", "remember-me")
                                .logoutSuccessUrl("/"))

        ;

    }


    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails nigina = User.builder()
                .username("nigina")
                .password(passwordEncoder.encode("123"))
                .roles(STUDENT.name())
                .authorities(STUDENT.getAuthorities())
                .build();

        UserDetails shoxista = User.builder()
                .username("shoxista")
                .password(passwordEncoder.encode("123"))
                .roles(ADMIN.name())
                .authorities(ADMIN.getAuthorities())
                .build();

        UserDetails akbar = User.builder()
                .username("akbar")
                .password(passwordEncoder.encode("123"))
                .roles(MANAGER.name())
                .authorities(MANAGER.getAuthorities())
                .build();

        return new InMemoryUserDetailsManager(nigina, shoxista, akbar);
    }


}
