package com.abid123.module1introduction.Handler;

import com.abid123.module1introduction.entities.User;
import com.abid123.module1introduction.services.JwtService;
import com.abid123.module1introduction.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
@RequiredArgsConstructor
public class OauthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;
    @Value("${deploy.env}")
    private String env;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth attributes:");

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) token.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        logger.info("Email: " + email);
        User user = userService.loadUserByEmail(email);
//        User user;
//        try {
//            user = (User) userService.loadUserByEmail(email);
//            log.info("Existing user found: {}", user);
//            if(user == null) { logger.info("yes user is null");}
//        } catch (BadCredentialsException ex) {
//            log.info("User not found, creating new user");
//
//            user = User.builder()
//                    .email(email)
//                    .name(oAuth2User.getAttribute("name"))
//                    .build();
//
//            userService.save(user);
//        }
        logger.info("User: " + user);
//        logger.info("User after creation: " + user);
        if (user == null) {

            User newUser = User.builder()
                    .email(email)
                    .name(oAuth2User.getAttribute("name"))
                    .build();

            userService.save(newUser);
        }
        logger.info("User after creation: " + user);

        String accessToken = jwtService.generateAccessToken(email);
        String refreshToken = jwtService.generateRefreshToken(email);


        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(env));
        response.addCookie(cookie);


        String frontEndUrl = "http://localhost:8080/home.html?token=" + accessToken;
        getRedirectStrategy().sendRedirect(request, response, frontEndUrl);

    }
}
