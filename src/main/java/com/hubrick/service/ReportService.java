package com.hubrick.service;

import com.hubrick.model.Department;
import com.hubrick.model.Employee;
import com.hubrick.model.ReportData;
import com.hubrick.repository.impl.DepartmentRepository;
import com.hubrick.repository.impl.EmployeeRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ROUND_CEILING;
import static java.util.Comparator.comparing;
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

    public ReportData getAverageIncomeByDepartment() {
        Map<Integer, BigDecimal> result = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId, averagingDouble(t -> t.getIncome().doubleValue())))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, t -> new BigDecimal(t.getValue()).setScale(2, ROUND_CEILING)));
        return new ReportData<>("department, average income \n", result);
    }

    public ReportData getAverageIncome95ByDepartment() {

        Map<Integer, List<Employee>> departmentEmployeeMap = employees.stream().collect(groupingBy(Employee::getDepartmentId));

        Map<Integer, BigDecimal> result = new HashMap<>();
        departmentEmployeeMap.forEach((key, value) -> {
            List<Employee> employeesSorted = value.stream().sorted(comparing(Employee::getIncome)).collect(Collectors.toList());
            Integer index95percentile = Math.toIntExact(Math.round(value.size() * 0.95));
            result.put(key, employeesSorted.get(index95percentile - 1).getIncome());
        });

        return new ReportData<>("department,average income \n", result);
    }

    public ReportData getAverageAgeByDepartment() {
        Map<Integer, BigDecimal> result = employees.stream()
                .collect(groupingBy(Employee::getDepartmentId, averagingDouble(Employee::getAge)))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, t -> new BigDecimal(t.getValue()).setScale(2, ROUND_CEILING)));
        return new ReportData<>("department,average age \n", result);
    }

    public ReportData getAverageIncomeByAgeFactorOfTen() {
        Map<Integer, Double> resultUnsorted = employees.stream().sorted(comparing(Employee::getAge))
                .collect(groupingBy(t -> (int) Math.ceil(t.getAge() / 10 * 10), Collectors.averagingDouble(t -> t.getIncome().doubleValue())));

        TreeMap<Integer, BigDecimal> result = new TreeMap<>();
        resultUnsorted.forEach((key, value) -> result.put(key, new BigDecimal(value).setScale(2, RoundingMode.CEILING)));
        return new ReportData<>("ag e, average income \n", result);
    }

}
