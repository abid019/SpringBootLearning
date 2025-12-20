package com.abid123.module1introduction.controllers;

import com.abid123.module1introduction.dto.EmployeeDTO;
import com.abid123.module1introduction.entities.EmployeeEntity;
import com.abid123.module1introduction.repositories.employeeRepository;
import com.abid123.module1introduction.services.EmployeeService;
import com.sun.source.tree.ReturnTree;
import jakarta.validation.Valid;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.abid123.module1introduction.Exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController {
//    @GetMapping(path = "/employee")
//    public String getEmployee() {
//        return "employee + detail";
//    }

    private final EmployeeService EmployeeService;      //constructor dependency injection
    public EmployeeController(EmployeeService EmployeeService) {
        this.EmployeeService = EmployeeService;
    }

    @GetMapping(path = "/{employeeId}")                 //get employee by id
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("employeeId") Long employeeId) throws ResourceNotFoundException {
        Optional<EmployeeDTO> OptionalEmployee = EmployeeService.findById(employeeId);
        return OptionalEmployee
                .map(Employee ->(ResponseEntity.ok(Employee)))
                .orElseThrow(()-> new ResourceNotFoundException(employeeId));
//        else return ResponseEntity.notFound().build();
    }

//    @ExceptionHandler(NoSuchFieldException.class)
//    public ResponseEntity<String> handleEmployeeNotFound(NoSuchFieldException e) {
//        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
//    }

    @GetMapping()                                       // get all employee list
    public ResponseEntity<List<EmployeeDTO> >employeeWithFilter(
            @RequestParam(required = false, name = "inputAge") Integer age,
            @RequestParam(defaultValue = "id", name = "sortBy") String SortField,
            @RequestParam(defaultValue = "ASC", name = "direction") String Direction,
            @RequestParam(defaultValue = "0", name = "pageNumber") String PageNumber
    ) {
        List<EmployeeDTO> AllEmploye = EmployeeService.findAll(age, SortField,Direction,PageNumber);
        return ResponseEntity.ok(AllEmploye);
    }

    @PostMapping()                                      // post employee
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO Employee) {
        EmployeeDTO employee = EmployeeService.save(Employee);
        return ResponseEntity.ok(employee);

    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @RequestBody @Valid EmployeeDTO employeeDto,
            @PathVariable("employeeId") long Id
    ) throws ResourceNotFoundException {
        EmployeeDTO employee = EmployeeService.updateEmployee(employeeDto, Id);
        if (employee != null) return ResponseEntity.ok(employee);
        else throw new ResourceNotFoundException(Id);
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> patchEmployee(@RequestBody Map<String,Object> employeeDTO, @PathVariable("employeeId") long Id) throws ResourceNotFoundException {
        EmployeeDTO employee = EmployeeService.patchEmployee(employeeDTO, Id);
        if(employee != null) return ResponseEntity.ok(employee);
        else throw new ResourceNotFoundException(Id);
    }
//
    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable("employeeId") long Id) throws ResourceNotFoundException {
        boolean gotDeleted = EmployeeService.deleteEmployee(Id);
        if(gotDeleted) return ResponseEntity.ok(true);
        else throw new ResourceNotFoundException(Id);
    }

}
