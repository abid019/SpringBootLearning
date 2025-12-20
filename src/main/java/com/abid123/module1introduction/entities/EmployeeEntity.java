package com.abid123.module1introduction.entities;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Persistent;

import java.time.LocalDate;

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
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
