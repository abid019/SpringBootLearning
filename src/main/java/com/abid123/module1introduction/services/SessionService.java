package com.abid123.module1introduction.services;

import com.abid123.module1introduction.entities.Session;
import com.abid123.module1introduction.entities.User;
import com.abid123.module1introduction.repositories.SessionRepository;
import com.abid123.module1introduction.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    private final int SESSION_LIMIT = 2;

    public void generateNewSession(User user, String refreshToken){
        List<Session> userSession = sessionRepository.findByUser(user);
        if(userSession.size() == SESSION_LIMIT){
            userSession.sort((o1, o2)-> o1.getLoginTime().compareTo(o2.getLoginTime()));
            Session LastSession = userSession.getFirst();
            sessionRepository.delete(LastSession);
        }

        Session session = Session.builder()
                .refreshToken(refreshToken)
                .user(user)
                .build();

        sessionRepository.save(session);

    }

    public void validateSession(String refreshToken){
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new SessionAuthenticationException("session expired"));

        session.setLoginTime(LocalDateTime.now());

        sessionRepository.save(session);
    }

}

