package com.abid123.module1introduction.services;

import com.abid123.module1introduction.dto.EmployeeDTO;
import com.abid123.module1introduction.entities.EmployeeEntity;
import com.abid123.module1introduction.repositories.employeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final employeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(employeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO findById(long Id){

          EmployeeEntity employee = employeeRepository.findById(Id).orElse(null);
          return modelMapper.map(employee, EmployeeDTO.class);
    }

    public List<EmployeeDTO> findAll(Integer age, String SortBy){
//        return employeeRepository.findAll();
        List<EmployeeEntity> AllEmployeesEntities = employeeRepository.findAll();
        List<EmployeeDTO> EmployeeDTOs = new ArrayList<>();
        for(EmployeeEntity employee : AllEmployeesEntities){
            EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
            EmployeeDTOs.add(employeeDTO);
        }
        return EmployeeDTOs;
    }

    public EmployeeDTO save(EmployeeEntity Employee) {
        EmployeeEntity SaveEmployee = employeeRepository.save(Employee);
        return modelMapper.map(SaveEmployee, EmployeeDTO.class);
    }
}
