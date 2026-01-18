package com.abid123.module1introduction.entities;

import com.abid123.module1introduction.entities.enums.Permissions;
import com.abid123.module1introduction.entities.enums.Role;
import com.abid123.module1introduction.utils.PermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String email;
    private String password;
    private String name;

    @ElementCollection(fetch=FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            Set<SimpleGrantedAuthority> permissions = PermissionMapping.getPermissions(role);
            authorities.addAll(permissions);
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}
