package com.abid123.module1introduction.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProjectAssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)    // owning side
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    @ToString.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
    private ManagerEntity manager;

    @ManyToOne//owning side
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
    private EmployeeEntity employee;
}
