package lv.javaguru.travel.insurance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withUsername("user1")
                .password(passwordEncoder().encode("javaguru1"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin2")
                .password(passwordEncoder().encode("javaguru2"))
                .roles("ADMIN")
                .build();

        UserDetails testUser = User.withUsername("testUser")
                .password(passwordEncoder().encode("javaguru3"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin, testUser);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/insurance/travel/api/**").authenticated()
                                .anyRequest().permitAll()
                )
                .httpBasic();
        return http.build();
    }

    /**
     * <b>Note:</b> Method must not be private.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
