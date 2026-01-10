package com.abid123.module1introduction.controllers;

import com.abid123.module1introduction.advices.ApiResponse;
import com.abid123.module1introduction.dto.*;
import com.abid123.module1introduction.services.AuthService;
import com.abid123.module1introduction.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    @Value("${deploy.env}")
    private String env;
    @PostMapping(path = "/signup")
    public ResponseEntity<UserDTO> SignupUpController(@RequestBody SingupDTO signupDTO) {
        UserDTO user = userService.singUpService(signupDTO);
        return ResponseEntity.ok(user);
    }



    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> LoginController(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) {
        LoginResponseDTO loginResponseDto = authService.login(loginDTO);

//      response.setHeader("Authorization", "Bearer " + token);
//      return ResponseEntity.ok(new ApiResponse<>("Logged in Success"));

        Cookie cookie = new Cookie("refresh_token", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(env));
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<LoginResponseDTO> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refresh_token"))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(()-> new AuthenticationServiceException("Refresh token not found"));

        LoginResponseDTO loginResponseDTO = authService.genrateAccessTokenFromRefreshToken(refreshToken);

        return ResponseEntity.ok(loginResponseDTO);

    }
}