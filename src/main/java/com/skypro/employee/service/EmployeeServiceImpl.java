package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<Integer, Employee> employees = new HashMap<>();

    private final Collection<Employee> employeesInDepartment = new ArrayList<>();
    private final Map<Integer, List<Employee>> employeesGroupedByDepartment = new HashMap<>();


    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }


    @Override
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

    @Override
    public int getSalarySum() {
        int salarySum = 0;
        for (Employee employee : employees.values()) {
             {
                salarySum += employee.getSalary();
            }
        }
        return salarySum;
    }

    public Employee getMinSalaryEmployee() {
        int minSalary = this.getSalarySum();
        Employee minSalaryEmployee = null;
//        Employee minSalaryEmployee = employees.get(0);
        for (Employee value : employees.values()) {
            if (value.getSalary() < minSalary) {
                minSalaryEmployee = value;
                minSalary = value.getSalary();
            }
        }
        return minSalaryEmployee;
    }

    public Employee getMaxSalaryEmployee() {
        int maxSalary = 0;
        Employee maxSalaryEmployee = null;
        for (Employee value : this.employees.values()) {
            if (value.getSalary() > maxSalary) {
                maxSalaryEmployee = value;
                maxSalary = value.getSalary();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeServiceImpl that = (EmployeeServiceImpl) o;
        return Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employees);
    }
}
