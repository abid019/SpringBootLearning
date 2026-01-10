package com.abid123.module1introduction.repositories;

import com.abid123.module1introduction.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    List<Session> findByUser(UserDetails user);

    Optional<Session> findByRefreshToken(String refreshToken);
}
