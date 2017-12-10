package com.hubrick.repository.impl;

import com.hubrick.file.reader.FileReader;
import com.hubrick.model.Department;
import com.hubrick.repository.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DepartmentRepository implements Repository<Department> {

    private static DepartmentRepository instance;

    private FileReader fileReader;
    private List<Department> departments;

    private DepartmentRepository(FileReader fileReader) {
        this.fileReader = fileReader;
        this.departments = loadDepartments();
    }

    public static synchronized DepartmentRepository getInstance(FileReader fileReader) {

        if (instance == null) {
            instance = new DepartmentRepository(fileReader);
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
            BufferedReader bufferedReader = fileReader.readFile("data/departments.csv");
            return bufferedReader.lines().sorted(Comparator.comparing(s -> s)).map(mapLineToDepartment).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
    }

    private Function<String, Department> mapLineToDepartment = (line) -> new Department.DepartmentBuilder().description(line).build();

}
