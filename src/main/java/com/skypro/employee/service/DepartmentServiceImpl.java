package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getAllEmployeesInDepartment(int departmentId) {
        return this.employeeService.getAllEmployees().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public int getSalarySum(int departmentId) {
        return this.employeeService.getAllEmployees().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary)
                .sum();

    }
    @Override
    public Map<Integer, List<Employee>> getAllEmployeesGrouped() {
        return getDepartments().stream()
                .collect(Collectors.toMap(dept -> dept, this::getAllEmployeesInDepartment));
    }

    public Set<Integer> getDepartments() {
        return employeeService.getAllEmployees().stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet());
    }

    @Override
    public OptionalInt getMxSalary(int departmentId) {
        return this.employeeService.getAllEmployees().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary)
                .max();
    }

    @Override
    public OptionalInt getMinSalary(int departmentId) {
        return this.employeeService.getAllEmployees().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary)
                .min();
    }
}
