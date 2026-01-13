package com.abid123.module1introduction.services;

import com.abid123.module1introduction.dto.LoginDTO;
import com.abid123.module1introduction.dto.LoginResponseDTO;
import com.abid123.module1introduction.dto.UserDTO;
import com.abid123.module1introduction.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final SessionService sessionService;
    private final UserService userService;

    public LoginResponseDTO login(LoginDTO LoginDTO) throws UsernameNotFoundException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(LoginDTO.getEmail(), LoginDTO.getPassword())
        );

        com.abid123.module1introduction.entities.User user = (com.abid123.module1introduction.entities.User) authentication.getPrincipal();
        log.info("User: {}", user);



        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        sessionService.generateNewSession(user,refreshToken);

        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();


    }


    public LoginResponseDTO genrateAccessTokenFromRefreshToken(String refreshToken) {

        String email = jwtService.getUserFromJwtToken(refreshToken);
        sessionService.validateSession(refreshToken);
        User user = userService.loadUserByEmail(email);
        String accessToken = jwtService.generateAccessToken(user);


        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }
}
