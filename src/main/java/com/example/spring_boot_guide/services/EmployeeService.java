package com.example.spring_boot_guide.services;


import com.example.spring_boot_guide.employees.Employee;
import com.example.spring_boot_guide.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public Employee createEmployee(Employee employee) {

        if(employee.getId() != null){
            throw new IllegalArgumentException("Id must be empty");
        }

        //unique email
        //salary > 5000
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }

        if(employee.getSalary() <= 5000){
            throw new IllegalArgumentException("Salary must be bigger than 5k");
        }



        return employeeRepository.save(employee);

    }
    @Transactional
    public void udpdateEmployee(Long id, String email, Integer salary){
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        if(email != null && !email.isEmpty() && employee.getEmail().equals(email)){
            employee.setEmail(email);
        }

        if(salary != null && salary > 5000){
            employee.setSalary(salary);
        }else{
            throw new IllegalArgumentException("Salary is null or less than 5k");
        }
    }
}
