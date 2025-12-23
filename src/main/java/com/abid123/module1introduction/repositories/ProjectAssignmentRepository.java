package com.abid123.module1introduction.repositories;

import com.abid123.module1introduction.entities.ProjectAssignmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectAssignmentRepository extends CrudRepository<ProjectAssignmentEntity, Long> {

}
