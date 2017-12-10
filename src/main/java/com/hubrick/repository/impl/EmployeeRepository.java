package com.hubrick.repository.impl;

import com.hubrick.model.Employee;
import com.hubrick.repository.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeRepository implements Repository<Employee> {

    private static EmployeeRepository instance;

    private List<Employee> employees;
    private Map<String, String> ages;

    public static synchronized EmployeeRepository getInstance() {

        if (instance == null) {
            instance = new EmployeeRepository();
        }

        return instance;
    }

    private EmployeeRepository() {
        this.ages = loadAges();
        this.employees = loadEmployees();
    }

    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public Optional<Employee> findOne(Integer id) {
        return employees.stream().filter(employee -> employee.getId().equals(id)).findFirst();
    }

    private List<Employee> loadEmployees() {

        try {

            InputStream inputStream = new FileInputStream(new File("data/employees.csv"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            return bufferedReader.lines().map(mapLineToEmployee).collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
    }

    private Map<String, String> loadAges() {

        try {

            InputStream inputStream = new FileInputStream(new File("data/ages.csv"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            return bufferedReader.lines().map(t -> t.split(",")).collect(Collectors.toMap(key -> key[0], value -> value[1]));

        } catch (FileNotFoundException e) {
            return Collections.emptyMap();
        }

    }

    private Function<String, Employee> mapLineToEmployee = (line) -> {

        String[] employeeAttributes = line.split(",");

        Integer departmentId = Optional.ofNullable(employeeAttributes[0]).map(Integer::valueOf).orElse(null);
        String name = Optional.ofNullable(employeeAttributes[1]).orElse(null);
        String gender = Optional.ofNullable(employeeAttributes[2]).orElse(null);
        BigDecimal income = Optional.ofNullable(employeeAttributes[3]).map(BigDecimal::new).orElse(null);
        Integer age = Optional.ofNullable(ages.get(name)).map(Integer::valueOf).orElse(null);

        return new Employee.EmployeeBuilder()
                .departmentId(departmentId)
                .name(name)
                .gender(gender)
                .income(income)
                .age(age)
                .build();
    };
}
