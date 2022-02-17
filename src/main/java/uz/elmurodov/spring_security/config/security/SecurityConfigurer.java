package uz.elmurodov.spring_security.config.security;

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

    private final PasswordEncoder passwordEncoder;

    public SecurityConfigurer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
//                .antMatchers("/secured-role-admin").hasRole("ADMIN")
//                .antMatchers("/secured-role-manager").hasAnyRole("ADMIN", "MANAGER")
//                .antMatchers("/secured-delete-manager").hasAuthority("manager_delete")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
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
