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
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DepartmentRepository implements Repository<Department> {

    @Override
    public List<Department> findAll() {

        try {

            InputStream inputStream = new FileInputStream(new File("data/departments.csv"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            return bufferedReader.lines().map(mapLineToDepartment).collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private Function<String, Department> mapLineToDepartment = Department::new;

}
