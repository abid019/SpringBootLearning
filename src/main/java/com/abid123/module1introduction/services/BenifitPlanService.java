package com.abid123.module1introduction.services;

import com.abid123.module1introduction.entities.BenifitPlanEntity;
import com.abid123.module1introduction.entities.EmployeeEntity;
import com.abid123.module1introduction.repositories.BenifitPlanRepository;
import com.abid123.module1introduction.repositories.employeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BenifitPlanService {

    private final BenifitPlanRepository benifitPlanRepository;
    private final employeeRepository employeeRepository;

    @Transactional
    public BenifitPlanEntity assignBenifitsToEmployee(BenifitPlanEntity benifitPlan, Long employeeId) {
        EmployeeEntity employee = employeeRepository.findById(employeeId).orElseThrow();
        employee.setBenifitPlan(benifitPlan); //dirtied employee = since the patient is dirtied hybernate will persist the benifitPlan. that why it's insert the value for benifit_plan
        benifitPlan.setEmployee(employee);

        return benifitPlan;
    }

    @Transactional
    public BenifitPlanEntity updateBenifitsToEmployee(BenifitPlanEntity benifitPlan, Long employeeId) {
        EmployeeEntity employee = employeeRepository.findById(employeeId).orElseThrow();
        employee.setBenifitPlan(benifitPlan); //dirtied employee = since the patient is dirtied hybernate will persist the benifitPlan. that why it's insert the value for benifit_plan
        benifitPlan.setEmployee(employee);
        return benifitPlan;
    }

    @Transactional
    public EmployeeEntity removeBenifitsToEmployee(Long employeeId) {
        EmployeeEntity employee = employeeRepository.findById(employeeId).orElseThrow();
        employee.setBenifitPlan(null); //dirtied employee = since the patient is dirtied hybernate will persist the benifitPlan. that why it's insert the value for benifit_plan

        return employee;
    }




}
