package com.hubrick.processor.impl;

import com.hubrick.exceptions.CSVWriteException;
import com.hubrick.model.ReportData;
import com.hubrick.repository.impl.DepartmentRepository;
import com.hubrick.repository.impl.EmployeeRepository;
import com.hubrick.service.FileService;
import com.hubrick.service.ReportService;
import com.hubrick.writer.impl.CSVWriter;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CSVProcessorTest {

    private FileService fileService = FileService.getInstance();

    private ReportService reportService;

    @Before
    public void setUp() throws Exception {
        EmployeeRepository employeeRepository = EmployeeRepository.getInstance(fileService);
        DepartmentRepository departmentRepository = DepartmentRepository.getInstance(fileService);
        this.reportService = ReportService.getInstance(employeeRepository, departmentRepository);
    }

    @Test(expected = CSVWriteException.class)
    public void processWithoutSuccess() {

        CSVWriter csvWriterMock = Mockito.mock(CSVWriter.class);
        CSVProcessor csvProcessor = CSVProcessor.getInstance(reportService, csvWriterMock);

        Mockito.when(csvWriterMock.write(Mockito.anyString(), Mockito.any(ReportData.class))).thenThrow(CSVWriteException.class);
        csvProcessor.process();
    }

}