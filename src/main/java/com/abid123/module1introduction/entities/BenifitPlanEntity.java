package com.abid123.module1introduction.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class BenifitPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String policyNumber;
    private String provider;
    private Date validUntil;
    private Date createdAt;

    @OneToOne(mappedBy = "benifitPlan")     //inverse side
    private EmployeeEntity employee;
}
