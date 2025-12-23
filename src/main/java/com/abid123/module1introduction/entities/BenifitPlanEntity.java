package com.abid123.module1introduction.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class BenifitPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String policyNumber;
    private String provider;
    private LocalDate validUntil;
    @CreationTimestamp
    private Date createdAt;

    @OneToOne(mappedBy = "benifitPlan", fetch = FetchType.LAZY)     //inverse side
    private EmployeeEntity employee;
}
