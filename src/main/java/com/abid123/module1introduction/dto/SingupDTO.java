package com.abid123.module1introduction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingupDTO {
    private String email;
    private String password;
    private String name;
}
