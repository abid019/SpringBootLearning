package com.abid123.module1introduction.services;

import com.abid123.module1introduction.Exceptions.ResourceNotFoundException;
import com.abid123.module1introduction.dto.SingupDTO;
import com.abid123.module1introduction.dto.UserDTO;
import com.abid123.module1introduction.entities.User;
import com.abid123.module1introduction.repositories.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    @Override
    public User loadUserByUsername(String username) throws BadCredentialsException {
        User u = userRepository.findByEmail(username)
                .orElseThrow(()-> new BadCredentialsException("Username not found"));
        log.info("User: " + u);
        return u;
    }

    public User loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    public User loadUserById(Long id) throws BadCredentialsException {
        return userRepository.findById(id)
                .orElse(null);
    }

    public UserDTO singUpService(SingupDTO singupDTO) {
        Optional<User> user = userRepository.findByEmail(singupDTO.getEmail());
        if (user.isPresent()) {
            throw new BadCredentialsException("User already exist ");
        }
        User toBeCreated = modelMapper.map(singupDTO, User.class);
        toBeCreated.setPassword(passwordEncoder.encode(toBeCreated.getPassword()));
        User savedUser = userRepository.save(toBeCreated);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    public void save(User newUser) {
        userRepository.save(newUser);
    }
}
