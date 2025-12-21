package com.abid123.module1introduction.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity

public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date createdAt;

    @ManyToMany     //owning side
    private Set<ManagerEntity> manager = new HashSet<>();

    @OneToOne
    private ManagerEntity headOfDepartment;
}
