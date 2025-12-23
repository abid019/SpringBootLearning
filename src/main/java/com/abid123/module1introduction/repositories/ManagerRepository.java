package com.abid123.module1introduction.repositories;

import com.abid123.module1introduction.entities.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, Long> {

}
