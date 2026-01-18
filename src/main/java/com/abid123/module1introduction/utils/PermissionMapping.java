package com.abid123.module1introduction.utils;

import com.abid123.module1introduction.entities.enums.Role;
import com.abid123.module1introduction.entities.enums.Permissions;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.abid123.module1introduction.entities.enums.Permissions.*;
import static com.abid123.module1introduction.entities.enums.Role.*;


import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class PermissionMapping {

    private static final Map<Role, Set<Permissions>> map = Map.of(
            USER, Set.of(EMPLOYEE_VIEW),
            CREATOR, Set.of(EMPLOYEE_VIEW,EMPLOYEE_UPDATE),
            ADMIN, Set.of(EMPLOYEE_VIEW,EMPLOYEE_UPDATE,EMPLOYEE_DELETE,EMPLOYEE_CREATE)
    );

    public static Set<SimpleGrantedAuthority> getPermissions(Role role) {
        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }

}
