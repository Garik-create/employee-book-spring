package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private EmployeeServiceImpl employeeService;
    private final Map<Integer, Employee> actualEmployeesMap = new HashMap<>();

    private List<Employee> actualEmployees;
//    private Employee employee;


    Employee employee1 = new Employee("Александр", "Пушкин", 1, 10000);
    Employee employee2 = new Employee("Василий", "Чапаев", 2, 20000);
    Employee employee3 = new Employee("Лев", "Толстой", 3, 30000);
    Employee employee4 = new Employee("Александр", "Блок", 1, 40000);

    EmployeeRequest employeeRequest5 = new EmployeeRequest();

    @BeforeEach
    public void setUp() {

        actualEmployees = List.of(employee1, employee2, employee3, employee4);

        employeeService = new EmployeeServiceImpl();

        EmployeeRequest employeeRequest = new EmployeeRequest();
        EmployeeRequest employeeRequest2 = new EmployeeRequest();
        EmployeeRequest employeeRequest3 = new EmployeeRequest();
        EmployeeRequest employeeRequest4 = new EmployeeRequest();

        employeeRequest.setFirstName(employee1.getFirstName());
        employeeRequest.setLastName(employee1.getLastName());
        employeeRequest.setDepartment(employee1.getDepartment());
        employeeRequest.setSalary(employee1.getSalary());

        employeeRequest2.setFirstName(employee2.getFirstName());
        employeeRequest2.setLastName(employee2.getLastName());
        employeeRequest2.setDepartment(employee2.getDepartment());
        employeeRequest2.setSalary(employee2.getSalary());

        employeeRequest3.setFirstName(employee3.getFirstName());
        employeeRequest3.setLastName(employee3.getLastName());
        employeeRequest3.setDepartment(employee3.getDepartment());
        employeeRequest3.setSalary(employee3.getSalary());

        employeeRequest4.setFirstName(employee4.getFirstName());
        employeeRequest4.setLastName(employee4.getLastName());
        employeeRequest4.setDepartment(employee4.getDepartment());
        employeeRequest4.setSalary(employee4.getSalary());

        employeeRequest5.setFirstName("Ivan");
        employeeRequest5.setLastName("Ivanov");
        employeeRequest5.setDepartment(1);
        employeeRequest5.setSalary(100_000);

        employeeService.addEmployee(employeeRequest);
        employeeService.addEmployee(employeeRequest2);
        employeeService.addEmployee(employeeRequest3);
        employeeService.addEmployee(employeeRequest4);
    }

    @Test
    void shouldGetAllEmployees() {
        Collection<Employee> expected = employeeService.getAllEmployees();
        List<Employee> actual = actualEmployees;
        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertTrue(expected.containsAll(actual));
//        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addEmployee() {
        Employee expected = employeeService.addEmployee(employeeRequest5);
        Employee actual = new Employee("Ivan", "Ivanov",
                1, 100_000);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldGetSalarySumEmployees() {
        Integer expected = employeeService.getSalarySum();
        Integer actual = actualEmployees.stream()
                .mapToInt(Employee::getSalary)
                .sum();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldGetEmployeeWithMinSalary() {
        Employee expected = employeeService.getMinSalaryEmployee();
        Employee actual = employee1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldGetEmployeeWithMaxSalary() {
        Employee expected = employeeService.getMaxSalaryEmployee();
        Employee actual = employee4;
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void getHighSalaryList() {
        OptionalDouble avgSalary = actualEmployees.stream()
                .mapToInt(Employee::getSalary)
                .average();
        List<Employee> actual = actualEmployees.stream()
                .filter(employee -> (double) employee.getSalary() > avgSalary.getAsDouble())
                .toList();
        Collection<Employee> expected = employeeService.getHighSalaryList();
        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertTrue(expected.containsAll(actual));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeServiceImplTest that = (EmployeeServiceImplTest) o;
        return Objects.equals(actualEmployees, that.actualEmployees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actualEmployees);
    }
}