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

import static com.hubrick.model.enums.ReportHeaderEnum.REPORT_AVERAGE_AGE_DEPARTMENT_HEADER;
import static com.hubrick.model.enums.ReportHeaderEnum.REPORT_AVERAGE_INCOME_DEPARTMENT_HEADER;
import static com.hubrick.model.enums.ReportHeaderEnum.REPORT_INCOME_AVERAGE_AGE_HEADER;
import static java.math.BigDecimal.ROUND_CEILING;
import static java.util.Comparator.comparing;

/**
 * This class hold all available reports business logic, it consolidates data, apply formulas and sorting
 *
 * @author eric.nascimento
 */
public class ReportService {

    private static final int SCALE_TWO = 2;
    private static final int FACTOR_TEN = 10;
    private static final double PERCENT_95 = 0.95;
    private static final String UNKNOWN = "Unknown";

    private static ReportService instance;

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    public static ReportService getInstance(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {

        if (instance == null) {
            instance = new ReportService(employeeRepository, departmentRepository);
        }
        return instance;
    }

    private ReportService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public ReportData getAverageIncomeByDepartment() {

        List<Employee> employees = employeeRepository.findAll();

        Map<String, BigDecimal> result = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId, Collectors.averagingDouble(t -> t.getIncome().doubleValue())))
                .entrySet().stream().collect(Collectors.toMap(k -> getDepartmentDescription(k.getKey()), t -> new BigDecimal(t.getValue()).setScale(2, ROUND_CEILING)));

        return new ReportData<>(REPORT_AVERAGE_INCOME_DEPARTMENT_HEADER.getHeader(), result);

    }

    public ReportData getAverageIncome95ByDepartment() {

        List<Employee> employees = employeeRepository.findAll();

        Map<Integer, List<Employee>> income95ByDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartmentId));

        Map<String, BigDecimal> result = new HashMap<>();
        income95ByDepartment.forEach((key, value) -> {
            List<Employee> employeesSorted = value.stream().sorted(comparing(Employee::getIncome)).collect(Collectors.toList());
            result.put(getDepartmentDescription(key), employeesSorted.get(Math.toIntExact(Math.round(value.size() * PERCENT_95)) - 1).getIncome());
        });

        return new ReportData<>(REPORT_AVERAGE_AGE_DEPARTMENT_HEADER.getHeader(), result);
    }

    public ReportData getAverageAgeByDepartment() {

        List<Employee> employees = employeeRepository.findAll();

        Map<String, BigDecimal> result = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId, Collectors.averagingDouble(Employee::getAge)))
                .entrySet().stream().collect(Collectors.toMap(k -> getDepartmentDescription(k.getKey()), t -> new BigDecimal(t.getValue()).setScale(2, ROUND_CEILING)));

        return new ReportData<>(REPORT_AVERAGE_AGE_DEPARTMENT_HEADER.getHeader(), result);
    }

    public ReportData getAverageIncomeByAgeFactorOfTen() {

        List<Employee> employees = employeeRepository.findAll();

        Map<Integer, Double> resultUnsorted = employees.stream().sorted(comparing(Employee::getAge))
                .collect(Collectors.groupingBy(t -> (int) Math.ceil(t.getAge() / FACTOR_TEN * FACTOR_TEN), Collectors.averagingDouble(t -> t.getIncome().doubleValue())));

        TreeMap<Integer, BigDecimal> result = new TreeMap<>();
        resultUnsorted.forEach((key, value) -> result.put(key, new BigDecimal(value).setScale(SCALE_TWO, RoundingMode.CEILING)));

        return new ReportData<>(REPORT_INCOME_AVERAGE_AGE_HEADER.getHeader(), result);
    }


    private String getDepartmentDescription(Integer departmentId) {
        Department department = departmentRepository.findOne(departmentId).orElse(null);
        return department != null ? department.getDescription() : UNKNOWN;
    }
}
