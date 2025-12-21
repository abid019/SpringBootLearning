package com.abid123.module1introduction.entities;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Persistent;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees",
    uniqueConstraints = {
//        @UniqueConstraint(name="email_unique", columnNames = {"email"}),
//        @UniqueConstraint(name = "email_name_unique", columnNames = {"email","name"})
    },
        indexes = {
            @Index(name = "email", columnList = "email")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String name;
    @Column(nullable = false)
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean active;
    @Column(name = "employee_role")
    private String role;
    private Double salary;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;

    @OneToOne  //owning side
    @JoinColumn(name = "benifit_plan_id", unique = true)
    private BenifitPlanEntity benifitPlan;

    @OneToMany(mappedBy = "employee")
    private Set<ProjectAssignmentEntity> projectAssignment = new HashSet<>();

}
