package com.hubrick.processor;

import com.hubrick.model.Department;
import com.hubrick.model.Employee;
import com.hubrick.repository.impl.DepartmentRepository;
import com.hubrick.repository.impl.EmployeeRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvProcessor extends AbstractProcessor {

    public CsvProcessor(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        super(employeeRepository, departmentRepository);
    }

    @Override
    protected boolean write(List<Department> departments, List<Employee> employees) {

        try {

            Map<Integer, Double> collect = employees.stream().collect(Collectors.groupingBy(Employee::getDepartmentId, Collectors.averagingDouble(Employee::getIncome)));
            System.out.println(collect);
            System.out.println(employees);

            byte[] bytes = "bla".getBytes();
            Path file = Paths.get("income-by-department.csv");
            Files.write(file, bytes);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
