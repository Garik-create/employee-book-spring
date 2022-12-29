package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import com.skypro.employee.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/employee")

public class EmployeeController {
    private final EmployeeService employeeService;

@Autowired
    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeService = employeeServiceImpl;
    }

    @GetMapping()
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @PostMapping()
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/salary/sum")
    public int getSalarySum() {

        return this.employeeService.getSalarySum();

    }

    @GetMapping("/salary/min")
    public Employee getMinSalaryEmployee() {

        return this.employeeService.getMinSalaryEmployee();
    }

    @GetMapping("/salary/max")
    public Employee getMaxSalaryEmployee() {
        return this.employeeService.getMaxSalaryEmployee();
    }



    @GetMapping("/high-salary")
    public Collection<Employee> getHighSalaryList() {
        return this.employeeService.getHighSalaryList();
    }

}
