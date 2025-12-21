package com.abid123.module1introduction;

import com.abid123.module1introduction.dto.EmployeeRoleStats;
import com.abid123.module1introduction.dto.IEmployee;
import com.abid123.module1introduction.dto.PEmployeeDTO;
import com.abid123.module1introduction.entities.EmployeeEntity;
import com.abid123.module1introduction.repositories.employeeRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class Module1introductionApplicationTests {

//	@Test
//	void contextLoads() {
//	}
    @Autowired
    private employeeRepository employeeRepository;
//


//    @Test
//    public void SimpleQuery() {
//        EmployeeEntity employeeEntity = EmployeeEntity
//                                            .builder()
//                                            .name("mdabidhussain")
//                                            .email("harryishere3112@gmail.com")
//                                            .age(18)
//                                            .dateOfJoining(LocalDate.of(2001, 12, 31))
//                                            .active(true)
//                                            .role("Full stack developer")
//                                            .salary(500000.00)
//                                            .build();
//        employeeRepository.save(employeeEntity);
//    }
//    @Test
//    public void CustomQueryMethod(){
//       List<EmployeeEntity> employeeEntities = employeeRepository.findBySalary(300000.0);
//  List<EmployeeEntity> employeeEntitie = employeeRepository.findByCreatedAtAfter(LocalDate.now());
//     List<EmployeeEntity> employeeEntities = employeeRepository.findByEmailOrName("mdabidhussain@gmail.com","nemat");
//         Optional<EmployeeEntity> employee = employeeRepository.findByEmailAndName("nemathussain@gmail.com","nemat");
//        employee.ifPresent(System.out::println);
//    }
//
//    @Test
//    public void JPQLQuery(){
//        Optional<EmployeeEntity> employee = employeeRepository.findByEmailAndName("nemathussain@gmail.com","nemat");
//        employee.ifPresent(System.out::println);
//    }

    @Test
    void contextLoads() {
//        List<IEmployee> employeeEntities = employeeRepository.getAllEmployees();
//        List<PEmployeeDTO> employeeEntities = employeeRepository.getAllEmployeesConcreate();
//        List<EmployeeRoleStats> employeeEntities = employeeRepository.getAllEmployeeStats();
//        for(EmployeeRoleStats employee: employeeEntities) {
//            System.out.println(employee);
//        }
          int EmployeId =  employeeRepository.updateEmployeeNameWithId("Md Abid Hussain", 111L);
          System.out.println(EmployeId);
    }
}
