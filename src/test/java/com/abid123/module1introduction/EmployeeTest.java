package com.abid123.module1introduction;

import com.abid123.module1introduction.entities.BenifitPlanEntity;
import com.abid123.module1introduction.entities.EmployeeEntity;
import com.abid123.module1introduction.repositories.employeeRepository;
import com.abid123.module1introduction.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void DeleteEmployeeTest() {
        employeeService.deleteEmployee(333L);
    }

    @Test
    public void testGetAllEmployee() {
        List<EmployeeEntity> EmployeeEntities = employeeService.TestGetAllEmployee();
        for(EmployeeEntity employee : EmployeeEntities) {
            System.out.println(employee);
        }
    }
}
