package com.abid123.module1introduction.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
public class ProjectAssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date startDate;
    private Date endDate;
    private boolean status;

    @ManyToOne()    // owning side
    @JoinColumn(name = "manager_id")
    private ManagerEntity manager;

    @ManyToOne//owning side
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
}
