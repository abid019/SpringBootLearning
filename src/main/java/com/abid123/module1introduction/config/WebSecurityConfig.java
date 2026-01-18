package com.abid123.module1introduction.config;

import com.abid123.module1introduction.Handler.OauthSuccessHandler;
import com.abid123.module1introduction.entities.enums.Role;

import static com.abid123.module1introduction.entities.enums.Permissions.*;
import static com.abid123.module1introduction.entities.enums.Role.ADMIN;

import com.abid123.module1introduction.filters.JwtValidationFilter;
import com.abid123.module1introduction.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtValidationFilter jwtValidationFilter;
    private final OauthSuccessHandler oauthSuccessHandler;


    private static final String[] publicRoutes = {
            "/auth/**" , "/error","/login/**","/oauth2/**", "/home.html"
    };
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(publicRoutes).permitAll()
                                .requestMatchers( HttpMethod.POST ,"/employee/**").hasAuthority(EMPLOYEE_CREATE.name())
                                .requestMatchers(HttpMethod.GET, "/employee/**").hasAuthority(EMPLOYEE_VIEW.name())
                                .requestMatchers(HttpMethod.DELETE,"/employee/**").hasAuthority(EMPLOYEE_DELETE.name())
                                .requestMatchers(HttpMethod.PUT,"/employee/**").hasAuthority(EMPLOYEE_UPDATE.name())
                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtValidationFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2Config -> oauth2Config
                        .successHandler(oauthSuccessHandler)
                        .failureUrl("/login?error=true")
                );

        return httpSecurity.build();
    }



}
