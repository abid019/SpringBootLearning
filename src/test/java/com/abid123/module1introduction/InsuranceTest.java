package com.abid123.module1introduction;

import com.abid123.module1introduction.entities.BenifitPlanEntity;
import com.abid123.module1introduction.entities.EmployeeEntity;
import com.abid123.module1introduction.services.BenifitPlanService;
import com.abid123.module1introduction.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {
//    @Autowired
//    private BenifitPlanService benifitPlanService;
//    @Autowired
//    private EmployeeService employeeService;
//
//    @Test
//    void testAssingBenifitPlan() {
//         BenifitPlanEntity benifitPlan = BenifitPlanEntity
//                                            .builder()
//                                            .policyNumber("HDFC_34G")
//                                            .provider("HDFC")
//                                            .validUntil(LocalDate.of(2030,1,1))
//                                            .build();
//
//        BenifitPlanEntity benifit = benifitPlanService.assignBenifitsToEmployee(benifitPlan,111L);
//        System.out.println(benifit);
//    }
//
//    @Test
//    void testRemoveBenifitPlan() {
//        EmployeeEntity employee = benifitPlanService.removeBenifitsToEmployee(111L);
//        System.out.println(employee);
//    }


}
