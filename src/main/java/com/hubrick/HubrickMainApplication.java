package com.hubrick;

import com.hubrick.processor.impl.CSVProcessor;
import com.hubrick.repository.impl.DepartmentRepository;
import com.hubrick.repository.impl.EmployeeRepository;
import com.hubrick.service.ReportService;
import com.hubrick.writer.impl.CSVWriter;

public class HubrickMainApplication {

    public static void main(String[] args) {

//        FactoryType.Type type = args.length == 1 ? FactoryType.Type.getTypeByName(args[0]) : FactoryType.Type.CSV;

        ReportService reportService = new ReportService(EmployeeRepository.getInstance(), DepartmentRepository.getInstance());
        CSVProcessor csvProcessor = new CSVProcessor(reportService, new CSVWriter());
        csvProcessor.process();

    }
}
