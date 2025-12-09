package com.abid123.module1introduction.controllers;

import com.abid123.module1introduction.dto.EmployeeDTO;
import com.abid123.module1introduction.entities.EmployeeEntity;
import com.abid123.module1introduction.repositories.employeeRepository;
import com.abid123.module1introduction.services.EmployeeService;
import com.sun.source.tree.ReturnTree;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        Optional<EmployeeDTO> OptionalEmployee = EmployeeService.findById(employeeId);
        return OptionalEmployee
                .map(Employee ->(ResponseEntity.ok(Employee)))
                .orElse(ResponseEntity.notFound().build());
//        else return ResponseEntity.notFound().build();
    }

    @GetMapping()                                       // get all employee list
    public ResponseEntity<List<EmployeeDTO> >employeeWithFilter(@RequestParam(required = false, name = "inputAge") Integer age,
                                                   @RequestParam(required = false) String SortBy) {
        List<EmployeeDTO> AllEmploye = EmployeeService.findAll(age, SortBy);
        return ResponseEntity.ok(AllEmploye);
    }

    @PostMapping()                                      // post employee
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeEntity Employee) {
        EmployeeDTO employee = EmployeeService.save(Employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO Employee,@PathVariable("employeeId") long Id) {
        EmployeeDTO employee = EmployeeService.updateEmployee(Employee,Id);
        if(employee != null) return ResponseEntity.ok(employee);
        else return ResponseEntity.notFound().build();
    }
//
    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> patchEmployee(@RequestBody Map<String,Object> employeeDTO, @PathVariable("employeeId") long Id) {
        EmployeeDTO employee = EmployeeService.patchEmployee(employeeDTO, Id);
        if(employee != null) return ResponseEntity.ok(employee);
        else return ResponseEntity.notFound().build();
    }
//
    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable("employeeId") long Id) {
        boolean gotDeleted = EmployeeService.deleteEmployee(Id);
        if(gotDeleted) return ResponseEntity.ok(true);
        else return ResponseEntity.notFound().build();
    }



//    1. put, delete  /
//    2. patch
//    3. isBoolean
//    4. response
}
