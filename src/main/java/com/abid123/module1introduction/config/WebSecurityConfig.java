package com.abid123.module1introduction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/employee").permitAll()
                                .requestMatchers("/employee/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails adminUser = User
                                    .withUsername("abid")
                                    .password(passwordEncoder().encode("abid123"))
                                    .roles("ADMIN")
                                    .build();

        UserDetails normalUser = User
                                    .withUsername("khalid")
                                    .password(passwordEncoder().encode("khalid123"))
                                    .roles("USER")
                                    .build();

        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
