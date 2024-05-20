package uz.anas.study_center.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uz.anas.study_center.error.LoginFailureHandler;
import uz.anas.study_center.error.LoginSuccessHandler;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final LoginFailureHandler loginFailureHandler;
    private final LoginSuccessHandler loginSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(manager -> {
            manager
                    .requestMatchers("/","/login", "/sign-up", "/css/**", "/logout").permitAll()
                    .anyRequest().authenticated();
        });
        http.formLogin(manager -> {
            manager
                    .loginPage("/login")
                    .failureHandler(loginFailureHandler)
                    .successHandler(loginSuccessHandler);
        });
        http.rememberMe(manager -> {
            manager
                    .useSecureCookie(false)
                    .rememberMeParameter("rem_me")
                    .tokenValiditySeconds(60 * 60 * 24 * 30);
        });

        http.userDetailsService(customUserDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
