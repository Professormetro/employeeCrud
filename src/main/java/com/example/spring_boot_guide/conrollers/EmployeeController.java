package com.example.spring_boot_guide.conrollers;


import com.example.spring_boot_guide.employees.Employee;
import com.example.spring_boot_guide.repository.EmployeeRepository;
import com.example.spring_boot_guide.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public List<Employee> hello(){
        return  employeeService.getAllEmployees();
    }

    @PostMapping("/")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id){
        if(employeeRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Employee with id " + id + " not found");
        }
        employeeRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable("id") Long  id,
                               @RequestParam(value="email", required = false) String email,
                               @RequestParam(value="salary",required = false) Integer salary){

        employeeService.udpdateEmployee(id, email, salary);

    }

}
