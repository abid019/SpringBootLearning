package com.abid123.module1introduction.controllers;

import com.abid123.module1introduction.dto.EmployeeDTO;
import com.abid123.module1introduction.entities.EmployeeEntity;
import com.abid123.module1introduction.repositories.employeeRepository;
import com.sun.source.tree.ReturnTree;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
//    @GetMapping(path = "/employee")
//    public String getEmployee() {
//        return "employee + detail";
//    }

    private final employeeRepository employeeRepository;        //constructor dependency injection
    EmployeeController(employeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/{employeeId}")                 //get employee by id
    public EmployeeEntity getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    @GetMapping()                                       // get all employee list
    public List<EmployeeEntity> employeeWithFilter(@RequestParam(required = false, name = "inputAge") Integer age,
                                                   @RequestParam(required = false) String SortBy) {
        return employeeRepository.findAll();
    }


    @PostMapping()                                      // post employee
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity Employee) {
        return employeeRepository.save(Employee);
//        return "Hello from post";
    }

//    @PutMapping()
//    public EmployeeEntity updateEmployee(@RequestBody EmployeeEntity Employee) {
//        return employeeRepository.save(Employee);
//    }
//
//    @PatchMapping()
//    public String patchEmployee(@RequestBody EmployeeDTO employeeDTO) {
//        return "hello from patch";
//    }
//
//    @DeleteMapping()
//    public String deleteEmployee(@RequestBody EmployeeDTO employeeDTO) {
//        return  "hello from delete";
//    }
}
