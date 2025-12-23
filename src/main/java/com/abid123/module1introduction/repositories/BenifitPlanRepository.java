package com.abid123.module1introduction.repositories;

import com.abid123.module1introduction.entities.BenifitPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenifitPlanRepository extends JpaRepository<BenifitPlanEntity, Long> {

}
