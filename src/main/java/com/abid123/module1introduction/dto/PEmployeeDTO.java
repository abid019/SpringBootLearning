package com.abid123.module1introduction.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PEmployeeDTO {
    private Long id;
    private String name;
    private Double salary;
}