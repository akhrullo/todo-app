package uz.elmurodov.spring_security.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
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

import java.util.concurrent.TimeUnit;

import static uz.elmurodov.spring_security.enums.Role.ADMIN;
import static uz.elmurodov.spring_security.enums.Role.MANAGER;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    public static final String[] WHITE_LIST = {
            "/", "/auth/login"
    };

    private final PasswordEncoder passwordEncoder;

    public SecurityConfigurer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .antMatchers(WHITE_LIST).permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(httpSecurityFormLoginConfigurer ->
                        httpSecurityFormLoginConfigurer
                                .loginPage("/auth/login")
                                .defaultSuccessUrl("/secured", false)
                                .loginProcessingUrl("/auth/login")
                )
                .rememberMe(httpSecurityRememberMeConfigurer -> {
                    httpSecurityRememberMeConfigurer
                            .key("!@#!@#W#ERFGSD$T##$%^%^$%$^%^@#$@#$WERREFG$#%")
                            .rememberMeParameter("remember-me")
                            .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(40));
                })
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer
                                .logoutUrl("/auth/logout")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID", "remember-me")
                );
        ;
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails aziza = User.builder()
                .username("aziza")
                .password(passwordEncoder.encode("aziza"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getAuthorities())
                .build();

        UserDetails jafarbek = User.builder()
                .username("ja")
                .password(passwordEncoder.encode("ja"))
//                .roles(MANAGER.name())
                .authorities(MANAGER.getAuthorities())
                .build();

        return new InMemoryUserDetailsManager(aziza, jafarbek);
    }


}
