package com.abid123.module1introduction.repositories;

import com.abid123.module1introduction.dto.EmployeeRoleStats;
import com.abid123.module1introduction.dto.IEmployee;
import com.abid123.module1introduction.dto.PEmployeeDTO;
import com.abid123.module1introduction.entities.EmployeeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

//    List<EmployeeEntity> findAll(Sort sort);
     Page<EmployeeEntity> findAll(Pageable pageable);

     @Query("SELECT e.id as id, e.name as name, e.salary as salary FROM EmployeeEntity e")
     List<IEmployee> getAllEmployees();

     @Query ("SELECT new com.abid123.module1introduction.dto.PEmployeeDTO(e.id , e.name, e.salary) FROM EmployeeEntity e")
     List<PEmployeeDTO> getAllEmployeesConcreate();

     @Query("SELECT new com.abid123.module1introduction.dto.EmployeeRoleStats(e.role, COUNT(e)) FROM EmployeeEntity e GROUP BY e.role ORDER BY COUNT(e)")
     List<EmployeeRoleStats> getAllEmployeeStats();

     @Transactional
     @Modifying
     @Query("UPDATE EmployeeEntity e SET e.name=:name WHERE e.id=:id")
     int updateEmployeeNameWithId(@Param("name") String name, @Param("id") Long id);

}
