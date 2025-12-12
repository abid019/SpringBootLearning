package com.abid123.module1introduction.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

//    @NotNull(message = "Id shouldn't be null")
   private Long id;

    @NotEmpty(message = "Name shouldn't be empty")
    @NotBlank(message = "name should be blank")
    @Size(min = 3, max = 10, message = "Name should be in the range")
   private String name;

    @Email(message = "Enter the correct Email")
   private String email;

   @Max(value = 20)
   @Min(value = 0)
   @PositiveOrZero(message = "age can be zero")
   private Integer age;

   @Past(message = "Date of joining must be past")
   private LocalDate dateOfJoining;

    @AssertTrue(message = "employee should have to be active")
   private Boolean active;

   @Pattern(regexp = "^(ADMIN|USER)$" , message = "the role of the employee can be user or admin")
   private String role;

   @DecimalMax(value = "10000.99")
   @DecimalMin(value = "100.99")
   @Digits(integer = 6, fraction = 2, message = "The salary can be in the form")
   private Double salary;

}
