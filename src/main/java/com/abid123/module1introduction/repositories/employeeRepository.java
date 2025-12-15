package com.abid123.module1introduction.repositories;

import com.abid123.module1introduction.entities.EmployeeEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface employeeRepository extends JpaRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findBySalary(double v);

    List<EmployeeEntity> findByCreatedAtAfter(LocalDate createdAtAfter);

    List<EmployeeEntity> findByEmailOrName(String email, String name);

    //    Optional<EmployeeEntity> findByEmailAndName(String email, String name);

    @Query("select e from EmployeeEntity e where e.email=:email and e.name=:name")
    Optional<EmployeeEntity> findByEmailAndName(String email, String name);
}
