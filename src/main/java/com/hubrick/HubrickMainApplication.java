package com.hubrick;

import com.hubrick.service.FileService;
import com.hubrick.processor.impl.CSVProcessor;
import com.hubrick.repository.impl.DepartmentRepository;
import com.hubrick.repository.impl.EmployeeRepository;
import com.hubrick.service.ReportService;
import com.hubrick.writer.impl.CSVWriter;

public class HubrickMainApplication {

    public static void main(String[] args) {

        CSVProcessor csvProcessor = getProcessor();
        boolean success = csvProcessor.process();

        if (success) {
            System.out.println("Reports successfully generated check project root folder");
        } else {
            System.out.println("Something went wrong, please check logs");
        }
    }

    private static CSVProcessor getProcessor() {

        EmployeeRepository employeeRepository = EmployeeRepository.getInstance(FileService.getInstance());
        DepartmentRepository departmentRepository = DepartmentRepository.getInstance(FileService.getInstance());

        ReportService reportService = ReportService.getInstance(employeeRepository, departmentRepository);
        return CSVProcessor.getInstance(reportService, CSVWriter.getInstance());
    }
}
