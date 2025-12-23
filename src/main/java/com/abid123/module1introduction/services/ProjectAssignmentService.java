package com.abid123.module1introduction.services;

import com.abid123.module1introduction.entities.EmployeeEntity;
import com.abid123.module1introduction.entities.ManagerEntity;
import com.abid123.module1introduction.entities.ProjectAssignmentEntity;
import com.abid123.module1introduction.repositories.ManagerRepository;
import com.abid123.module1introduction.repositories.ProjectAssignmentRepository;
import com.abid123.module1introduction.repositories.employeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectAssignmentService {
    private final ProjectAssignmentRepository projectAssignmentRepository;
    private final ManagerRepository managerRepository;
    private final employeeRepository employeeRepository;

    @Transactional
    public ProjectAssignmentEntity AssignProject(ProjectAssignmentEntity project, Long employeeId, Long ManagerId) {
        EmployeeEntity employee = employeeRepository.findById(employeeId).orElse(null);
        ManagerEntity manager = managerRepository.findById(ManagerId).orElse(null);
        project.setEmployee(employee);
        project.setManager(manager);
        projectAssignmentRepository.save(project);
        return project;
    }

}
