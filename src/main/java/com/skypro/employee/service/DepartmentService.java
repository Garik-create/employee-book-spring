package com.skypro.employee.service;

import com.skypro.employee.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

public interface DepartmentService {
    Collection<Employee> getAllEmployeesInDepartment(int departmentId);

    int getSalarySum(int departmentId);

    OptionalInt getMxSalary(int departmentId);

    OptionalInt getMinSalary(int departmentId);

//    Map<Integer, List<Employee>> getAllEmployeesGrouped();

//    Map<Integer, List<Employee>> getAllEmployeesGrouped(List<Employee> employees);

    Map<Integer, List<Employee>> getAllEmployeesGrouped();
}

