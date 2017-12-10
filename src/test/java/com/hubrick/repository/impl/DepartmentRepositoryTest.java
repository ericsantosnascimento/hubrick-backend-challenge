package com.hubrick.repository.impl;

import com.hubrick.service.FileService;
import com.hubrick.model.Department;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentRepositoryTest {

    private DepartmentRepository departmentRepository;

    private FileService fileService = FileService.getInstance();

    @Before
    public void setUp() throws Exception {
        this.departmentRepository = DepartmentRepository.getInstance(fileService);
    }

    @Test
    public void getInstanceValidateSingletonWithSuccess() {
        DepartmentRepository departmentRepository0 = DepartmentRepository.getInstance(fileService);
        DepartmentRepository departmentRepository1 = DepartmentRepository.getInstance(fileService);
        Assert.assertThat(departmentRepository1, CoreMatchers.is(CoreMatchers.equalTo(departmentRepository0)));
    }

    @Test
    public void findAllWithSuccess() {
        List<Department> departments = departmentRepository.findAll();
        Assert.assertThat(departments, CoreMatchers.notNullValue());
        Assert.assertThat(departments.size(), CoreMatchers.is(7));
    }

    @Test
    public void findAllThrowsFileNotFound() throws Exception {
        FileService fileServiceMock = Mockito.mock(FileService.class);
        DepartmentRepository departmentRepositoryMock = Mockito.mock(DepartmentRepository.class);
        Mockito.when(fileServiceMock.readFile("data/departments.csv")).thenThrow(FileNotFoundException.class);
        List<Department> departments = departmentRepositoryMock.findAll();
        Assert.assertThat(departments, CoreMatchers.notNullValue());
        Assert.assertThat(departments.size(), CoreMatchers.is(0));
    }

    @Test
    public void findOne() {
        Optional<Department> department = departmentRepository.findOne(1);
        Assert.assertThat(department.get(), CoreMatchers.notNullValue());
    }

    @Test
    public void findOneNotFound() {
        Optional<Department> department = departmentRepository.findOne(1421);
        Assert.assertThat(department.isPresent(), CoreMatchers.is(false));
    }
}