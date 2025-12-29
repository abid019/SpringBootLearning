package com.abid123.module1introduction.services;

import com.abid123.module1introduction.Exceptions.ResourceNotFoundException;
import com.abid123.module1introduction.repositories.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        ()-> {
                            throw new UsernameNotFoundException("Username not found");
                        }
                );
    }

}
