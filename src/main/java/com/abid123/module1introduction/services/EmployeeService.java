package com.abid123.module1introduction.services;

import com.abid123.module1introduction.dto.EmployeeDTO;
import com.abid123.module1introduction.entities.EmployeeEntity;
import com.abid123.module1introduction.repositories.BenifitPlanRepository;
import com.abid123.module1introduction.repositories.employeeRepository;
import jakarta.transaction.Transactional;
import org.apache.el.util.ReflectionUtil;
//import org.hibernate.query.Page;
import org.hibernate.query.SortDirection;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final BenifitPlanRepository benifitPlanRepository;

    public EmployeeService(employeeRepository employeeRepository, ModelMapper modelMapper, BenifitPlanRepository benifitPlanRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.benifitPlanRepository = benifitPlanRepository;
    }

    public Optional<EmployeeDTO> findById(long Id){

          Optional<EmployeeEntity> OptionalEmployee = employeeRepository.findById(Id);
//          return modelMapper.map(employee, EmployeeDTO.class);
          return OptionalEmployee.map(employeeEntity ->{
               return modelMapper.map(employeeEntity, EmployeeDTO.class);
          });
    }

//    public List<EmployeeDTO> findAll(Integer age, String SortField,String Direction){
////        return employeeRepository.findAll();
//        System.out.println("salarySortBy-> " + SortField);
////        Sort.Direction direction = Direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
//         Sort.Direction direction;
//        try{
//            direction = Sort.Direction.valueOf(Direction.toUpperCase());
//        }catch(IllegalArgumentException e){
//            direction = Sort.Direction.ASC;
//        }
//
//        Pageable pageable
//        List<EmployeeEntity> AllEmployeesEntities = employeeRepository.findAll(Sort.by(direction, SortField ));
//        List<EmployeeDTO> EmployeeDTOs = new ArrayList<>();
//        for(EmployeeEntity employee : AllEmployeesEntities){
//            EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
//            EmployeeDTOs.add(employeeDTO);
//        }
//        return EmployeeDTOs;
//    }


public List<EmployeeDTO> findAll(Integer age, String SortField,String Direction,String pageNumber){
//        return employeeRepository.findAll();
    System.out.println("salarySortBy-> " + SortField);
//        Sort.Direction direction = Direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    Sort.Direction direction;
    try{
        direction = Sort.Direction.valueOf(Direction.toUpperCase());
    }catch(IllegalArgumentException e){
        direction = Sort.Direction.ASC;
    }

    Sort sort = Sort.by(direction, SortField);
    Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber),10,sort);

    List<EmployeeEntity> AllEmployeesEntities = employeeRepository.findAll(pageable).getContent();
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
        boolean exist = isExist(Id);
        if(!exist)  return null;
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

    @Transactional
    public void deleteEmployee(Long employeeId) {
        benifitPlanRepository.findById(employeeId).orElseThrow();
        benifitPlanRepository.deleteById(employeeId);
    }


    public List<EmployeeEntity> removeBenifitsToEmployee() {
        List<EmployeeEntity> EmployeeEntities = employeeRepository.getAllEmployeeWithBenifits();
        return EmployeeEntities;
    }

    public List<EmployeeEntity> TestGetAllEmployee() {
        List<EmployeeEntity> EmployeeEntities = employeeRepository.getAllEmployeeWithBenifits();
        return EmployeeEntities;
    }
}
