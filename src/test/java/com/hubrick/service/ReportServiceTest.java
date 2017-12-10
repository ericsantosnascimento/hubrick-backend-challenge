package com.hubrick.service;

import com.hubrick.model.ReportData;
import com.hubrick.model.enums.ReportHeaderEnum;
import com.hubrick.repository.impl.DepartmentRepository;
import com.hubrick.repository.impl.EmployeeRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ReportServiceTest {

    private ReportService reportService;

    private FileService fileService = FileService.getInstance();

    @Before
    public void setUp() throws Exception {
        EmployeeRepository employeeRepository = EmployeeRepository.getInstance(fileService);
        DepartmentRepository departmentRepository = DepartmentRepository.getInstance(fileService);
        this.reportService = ReportService.getInstance(employeeRepository, departmentRepository);
    }

    @Test
    public void getAverageIncomeByDepartmentWithSuccess() {
        ReportData averageAgeByDepartment = reportService.getAverageIncomeByDepartment();
        Assert.assertThat(averageAgeByDepartment.getHeader(), is(equalTo(ReportHeaderEnum.REPORT_AVERAGE_INCOME_DEPARTMENT_HEADER.getHeader())));
        Assert.assertThat(averageAgeByDepartment.getContent(), is(notNullValue()));
        Assert.assertThat(averageAgeByDepartment.getContent().size(), is(8));
    }

    @Test
    public void getAverageIncome95ByDepartmentWithSuccess() {
        ReportData averageIncome95ByDepartment = reportService.getAverageIncome95ByDepartment();
        Assert.assertThat(averageIncome95ByDepartment.getHeader(), is(equalTo(ReportHeaderEnum.REPORT_AVERAGE_AGE_DEPARTMENT_HEADER.getHeader())));
        Assert.assertThat(averageIncome95ByDepartment.getContent(), is(notNullValue()));
        Assert.assertThat(averageIncome95ByDepartment.getContent().size(), CoreMatchers.is(8));
    }

    @Test
    public void getAverageAgeByDepartment() {
        ReportData averageAgeByDepartment = reportService.getAverageAgeByDepartment();
        Assert.assertThat(averageAgeByDepartment.getHeader(), is(equalTo(ReportHeaderEnum.REPORT_AVERAGE_AGE_DEPARTMENT_HEADER.getHeader())));
        Assert.assertThat(averageAgeByDepartment.getContent(), is(notNullValue()));
        Assert.assertThat(averageAgeByDepartment.getContent().size(), is(8));
    }

    @Test
    public void getAverageIncomeByAgeFactorOfTen() {
        ReportData averageIncomeByAgeFactorOfTen = reportService.getAverageIncomeByAgeFactorOfTen();
        Assert.assertThat(averageIncomeByAgeFactorOfTen.getHeader(), is(equalTo(ReportHeaderEnum.REPORT_INCOME_AVERAGE_AGE_HEADER.getHeader())));
        Assert.assertThat(averageIncomeByAgeFactorOfTen.getContent(), is(notNullValue()));
        Assert.assertThat(averageIncomeByAgeFactorOfTen.getContent().size(), CoreMatchers.is(8));
    }
}