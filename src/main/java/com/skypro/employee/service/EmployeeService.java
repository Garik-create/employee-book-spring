package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Employee Name should be set");
        }
        Employee employee = new Employee(
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getMinSalaryEmployee() {
        int minSalary = employees.get(0).getSalary();
        Employee minSalaryEmployee = employees.get(0);
        for (Employee value : employees.values()) {
            if (value.getSalary() < minSalary) {
                minSalaryEmployee = value;
            }
        }
        return minSalaryEmployee;
    }

    public Employee getMaxSalaryEmployee() {
        int maxSalary = this.employees.get(0).getSalary();
        Employee maxSalaryEmployee = this.employees.get(0);
        for (Employee value : this.employees.values()) {
            if (value.getSalary() > maxSalary) {
                maxSalaryEmployee = value;
            }
        }
        return maxSalaryEmployee;
    }

    public Collection<Employee> getHighSalaryList() {

        Collection<Employee> highSalaryList = new HashSet<>();
        OptionalDouble average = this.employees.values().stream()
                .mapToInt(Employee::getSalary)
                .average();

        for (Map.Entry<Integer, Employee> entry : this.employees.entrySet()) {
            if (entry.getValue().getSalary() > average.getAsDouble()) {
                highSalaryList.add(entry.getValue());
            }
        }
        return highSalaryList;
    }
}
