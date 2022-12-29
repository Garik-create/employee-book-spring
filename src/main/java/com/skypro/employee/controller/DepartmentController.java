package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.lang.String.format;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public Collection<Employee> getAllEmployeesInDepartment(@PathVariable("id") int departmentId) {
              return   this.departmentService.getAllEmployeesInDepartment(departmentId);

    }

    @GetMapping("/{id}/salary/sum")
    public String getSalarySumInDepartment(@PathVariable("id") int departmentId) {
        return format("Сумма зарплат в департаменте %d равна %d рублей.", departmentId,
                this.departmentService.getSalarySum(departmentId));
    }

    @GetMapping("/{id}/salary/max")
    public OptionalInt getMaxSalaryInDepartment(@PathVariable("id") int departmentId) {
        return this.departmentService.getMxSalary(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public OptionalInt getMinSalaryInDepartment(@PathVariable("id") int departmentId) {
        return this.departmentService.getMinSalary(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllEmployeesGrouped() {
        return this.departmentService.getAllEmployeesGrouped();
    }
}
