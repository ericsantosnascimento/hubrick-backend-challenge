package com.hubrick.repository.impl;

import com.hubrick.model.Department;
import com.hubrick.repository.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DepartmentRepository implements Repository<Department> {

    private static DepartmentRepository instance;

    private List<Department> departments;

    private DepartmentRepository() {
        this.departments = loadDepartments();
    }

    public static synchronized DepartmentRepository getInstance() {

        if (instance == null) {
            instance = new DepartmentRepository();
        }

        return instance;
    }

    @Override
    public List<Department> findAll() {
        return departments;
    }

    @Override
    public Optional<Department> findOne(Integer id) {
        return departments.stream().filter(department -> department.getId().equals(id)).findFirst();
    }

    private List<Department> loadDepartments() {

        try {

            InputStream inputStream = new FileInputStream(new File("data/departments.csv"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            return bufferedReader.lines().sorted(Comparator.comparing(s -> s)).map(mapLineToDepartment).collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
    }

    private Function<String, Department> mapLineToDepartment = (line) -> new Department.DepartmentBuilder().description(line).build();

}
