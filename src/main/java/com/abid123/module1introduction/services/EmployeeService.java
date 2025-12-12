package com.abid123.module1introduction.services;

import com.abid123.module1introduction.dto.EmployeeDTO;
import com.abid123.module1introduction.entities.EmployeeEntity;
import com.abid123.module1introduction.repositories.employeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    private final employeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(employeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> findById(long Id){

          Optional<EmployeeEntity> OptionalEmployee = employeeRepository.findById(Id);
//          return modelMapper.map(employee, EmployeeDTO.class);
          return OptionalEmployee.map(employeeEntity ->{
               return modelMapper.map(employeeEntity, EmployeeDTO.class);
          });
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

    public EmployeeDTO save(EmployeeDTO Employee) {
        EmployeeEntity EmployeeEntity = modelMapper.map(Employee, EmployeeEntity.class);
        EmployeeEntity SaveEmployee = employeeRepository.save(EmployeeEntity);
        return modelMapper.map(SaveEmployee, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployee(EmployeeDTO Employee,long Id){
        EmployeeEntity EmployeeEntity = modelMapper.map(Employee,EmployeeEntity.class);
        EmployeeEntity.setId(Id);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(EmployeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean isExist(long Id){
        return employeeRepository.existsById(Id);
    }

    public boolean deleteEmployee(long Id){
        boolean exist = isExist(Id);
        if(!exist)  return false;
        employeeRepository.deleteById(Id);
        return true;
    }

    public EmployeeDTO patchEmployee(Map<String, Object> employeeDTO, long Id){
        boolean exist = isExist(Id);
        if(!exist)  return null;
        EmployeeEntity EmployeeEntity = employeeRepository.findById(Id).get();
        employeeDTO.forEach((field, Value)->{
            Field toBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
            ReflectionUtils.makeAccessible(toBeUpdated);
            ReflectionUtils.setField(toBeUpdated, EmployeeEntity, Value);
        });
        return modelMapper.map(EmployeeEntity, EmployeeDTO.class);
    }
}
