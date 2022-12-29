package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(EmployeeRequest employeeRequest);

    int getSalarySum();

    Employee getMinSalaryEmployee();

    Employee getMaxSalaryEmployee();

    Collection<Employee> getHighSalaryList();

    Collection<Employee> getAllEmployees();

}
