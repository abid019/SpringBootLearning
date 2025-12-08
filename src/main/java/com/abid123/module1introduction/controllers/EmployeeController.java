package com.abid123.module1introduction.controllers;

import com.abid123.module1introduction.dto.EmployeeDTO;
import com.abid123.module1introduction.entities.EmployeeEntity;
import com.abid123.module1introduction.repositories.employeeRepository;
import com.abid123.module1introduction.services.EmployeeService;
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

    private final EmployeeService EmployeeService;      //constructor dependency injection
    EmployeeController(employeeRepository employeeRepository, EmployeeService EmployeeService) {
        this.EmployeeService = EmployeeService;
    }

    @GetMapping(path = "/{employeeId}")                 //get employee by id
    public EmployeeDTO getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        return EmployeeService.findById(employeeId);
    }

    @GetMapping()                                       // get all employee list
    public List<EmployeeDTO> employeeWithFilter(@RequestParam(required = false, name = "inputAge") Integer age,
                                                   @RequestParam(required = false) String SortBy) {
        return EmployeeService.findAll(age, SortBy);
    }

    @PostMapping()                                      // post employee
    public EmployeeDTO createEmployee(@RequestBody EmployeeEntity Employee) {
        return EmployeeService.save(Employee);
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
