package com.abid123.module1introduction.controllers;

import com.abid123.module1introduction.dto.EmployeeDTO;
import com.sun.source.tree.ReturnTree;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
//    @GetMapping(path = "/employee")
//    public String getEmployee() {
//        return "employee + detail";
//    }

    @GetMapping(path = "{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        return new EmployeeDTO(employeeId, "abid", "abid@gmail.com", 23, LocalDate.of(2008, 11, 23), true );
    }

    @GetMapping()
    public  String employeeWithFilter(@RequestParam(required = false, name = "inputAge") Integer age,
                                      @RequestParam(required = false) String SortBy) {
        return "Hi my age is " + age +" "+SortBy;
    }
    @PostMapping()
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO Employee) {
        Employee.setId(100L);
        return(Employee);
//        return "Hello from post";
    }

    @PutMapping()
    public String updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return "hello from put";
    }

    @PatchMapping()
    public String patchEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return "hello from patch";
    }

    @DeleteMapping()
    public String deleteEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return  "hello from delete";
    }
}
