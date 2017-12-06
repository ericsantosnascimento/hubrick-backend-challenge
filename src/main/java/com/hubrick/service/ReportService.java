package com.hubrick.service;

import com.hubrick.model.Department;
import com.hubrick.model.Employee;
import com.hubrick.repository.impl.DepartmentRepository;
import com.hubrick.repository.impl.EmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

public class ReportService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    private List<Employee> employees;
    private List<Department> departments;

    public ReportService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;

        this.employees = this.employeeRepository.findAll();
        this.departments = this.departmentRepository.findAll();
    }

    public Map<Integer, Double> getIncomeByDepartment() {
        return employees.stream().collect(groupingBy(Employee::getDepartmentId, averagingDouble(Employee::getIncome)));
    }

    public Map<Integer, Double> getIncome95ByDepartment() {
        List<Employee> collect = employees.stream().sorted(Comparator.comparing(Employee::getIncome)).collect(Collectors.toList());
        return collect.stream().sorted(Comparator.comparing(Employee::getIncome))
               .collect(groupingBy(Employee::getDepartmentId, averagingDouble(Employee::getIncome)));
    }
}
