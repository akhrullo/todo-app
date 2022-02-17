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


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfigurer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/css/**")
                .permitAll()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/student-profile").hasAuthority("view_student_mark")
//                .hasAnyRole("STUDENT", "TEACHER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
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
