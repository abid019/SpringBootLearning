package com.abid123.module1introduction.dto;

import com.abid123.module1introduction.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingupDTO {
    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
}
