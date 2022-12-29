package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    List<Employee> actualEmployees;
    int departmentId = 1;


    @BeforeEach
    public void setUp() {
        Employee employee1 = new Employee("Александр", "Пушкин", 1, 10000);
        Employee employee2 = new Employee("Василий", "Чапаев", 1, 20000);
        Employee employee3 = new Employee("Лев", "Толстой", 1, 30000);
        Employee employee4 = new Employee("Александр", "Блок", 2, 40000);
        Employee employee5 = new Employee("Фёдор", "Тютчев", 2, 50000);
        Employee employee6 = new Employee("Сергей", "Есенин", 2, 60000);
        Employee employee7 = new Employee("Сергей", "Бодров", 3, 70000);
        Employee employee8 = new Employee("Афанасий", "Фет", 3, 80000);
        Employee employee9 = new Employee("Константин", "Паустовский", 3, 90000);

        actualEmployees = new ArrayList<>(List.of(employee1, employee2, employee3,
                employee4, employee5, employee6, employee7, employee8, employee9));



        when(employeeService.getAllEmployees()).thenReturn(actualEmployees);
    }

    @Test
    void shouldReturnAllEmployeesInDepartment() {
        List<Employee> actual = actualEmployees.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
        List<Employee> expected = departmentService.getAllEmployeesInDepartment(departmentId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnSalarySumInDepartment() {
        int actual = actualEmployees.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary)
                .sum();
        int expected = departmentService.getSalarySum(departmentId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnMaxSalaryInDepartment() {
        OptionalInt expected = departmentService.getMxSalary(departmentId);
        OptionalInt actual = OptionalInt.of(30000);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void shouldReturnMinSalaryInDepartment() {
        OptionalInt expected = departmentService.getMinSalary(departmentId);
        OptionalInt actual = OptionalInt.of(10000);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void shouldReturnAllEmployeesGrouped() {
        Map<Integer, List<Employee>> expected = departmentService.getAllEmployeesGrouped();

        Map<Integer, List<Employee>> actual = actualEmployees.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet())
                .stream().collect(Collectors.toMap(dept -> dept, dept -> actualEmployees.stream()
                        .filter(employee -> employee.getDepartment() == dept)
                        .collect(Collectors.toList())));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnSetOfDepartments() {
        Set<Integer> expected = departmentService.getDepartments();
        Set<Integer> actual = new HashSet<>(List.of(1, 2, 3));
        Assertions.assertEquals(expected, actual);
    }
}