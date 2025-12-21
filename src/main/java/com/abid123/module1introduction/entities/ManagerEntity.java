package com.abid123.module1introduction.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class ManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;
    private String email;
    private Date dateOfBirth;

    @OneToMany(mappedBy = "manager")  //Inversion side
    private Set<ProjectAssignmentEntity> projectAssignment = new HashSet<>();

    @ManyToMany(mappedBy = "manager")   //Inversion side
    private Set<DepartmentEntity> department = new HashSet<>();


}
