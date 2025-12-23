package com.abid123.module1introduction;

import com.abid123.module1introduction.entities.BenifitPlanEntity;
import com.abid123.module1introduction.services.BenifitPlanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {
    @Autowired
    private BenifitPlanService benifitPlanService;

    @Test
    void testAssingBenifitPlan() {
         BenifitPlanEntity benifitPlan = BenifitPlanEntity
                                            .builder()
                                            .policyNumber("HDFC_34G")
                                            .provider("HDFC")
                                            .validUntil(LocalDate.of(2030,1,1))
                                            .build();

        BenifitPlanEntity benifit = benifitPlanService.assignBenifitsToEmployee(benifitPlan,111L);
        System.out.println(benifit);
    }


}
