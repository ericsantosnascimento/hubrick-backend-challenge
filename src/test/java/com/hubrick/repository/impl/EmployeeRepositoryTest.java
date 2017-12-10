package com.hubrick.repository.impl;

import com.hubrick.service.FileService;
import com.hubrick.model.Employee;
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
public class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;

    private FileService fileService = FileService.getInstance();

    @Before
    public void setUp() throws Exception {
        this.employeeRepository = EmployeeRepository.getInstance(fileService);
    }

    @Test
    public void getInstanceValidateSingletonWithSuccess() {
        EmployeeRepository employeeRepository0 = EmployeeRepository.getInstance(fileService);
        EmployeeRepository employeeRepository1 = EmployeeRepository.getInstance(fileService);
        Assert.assertThat(employeeRepository0, CoreMatchers.is(CoreMatchers.equalTo(employeeRepository1)));
    }

    @Test
    public void findAllWithSuccess() {
        List<Employee> employees = employeeRepository.findAll();
        Assert.assertThat(employees, CoreMatchers.notNullValue());
        Assert.assertThat(employees.size(), CoreMatchers.is(100));
    }

    @Test
    public void findAllThrowsFileNotFound() throws Exception {
        FileService fileServiceMock = Mockito.mock(FileService.class);
        EmployeeRepository employeeRepositoryMock = Mockito.mock(EmployeeRepository.class);
        Mockito.when(fileServiceMock.readFile("data/employees.csv")).thenThrow(FileNotFoundException.class);
        List<Employee> departments = employeeRepositoryMock.findAll();
        Assert.assertThat(departments, CoreMatchers.notNullValue());
        Assert.assertThat(departments.size(), CoreMatchers.is(0));
    }

    @Test
    public void findOne() {
        Optional<Employee> employee = employeeRepository.findOne(1);
        Assert.assertThat(employee.get(), CoreMatchers.notNullValue());
    }

    @Test
    public void findOneNotFound() {
        Optional<Employee> employee = employeeRepository.findOne(1421);
        Assert.assertThat(employee.isPresent(), CoreMatchers.is(false));
    }
}