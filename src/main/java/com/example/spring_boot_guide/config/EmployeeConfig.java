package com.example.spring_boot_guide.config;


import com.example.spring_boot_guide.employees.Employee;
import com.example.spring_boot_guide.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class EmployeeConfig {
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
        return (args) -> {
            var employeeList = List.of(
                    new Employee(1L, "SFDsFSD", "fdjgdfjkgkfdjg", LocalDate.of(2000, 12, 1), 12313321),
                    new Employee(2L, "SDFJSDfjkfds", "gfdssdfglkpdfskgdsfklgdsflk", LocalDate.of(2000, 10, 1),  12313321)
            );

            employeeRepository.saveAll(employeeList);
        };
    }
}
