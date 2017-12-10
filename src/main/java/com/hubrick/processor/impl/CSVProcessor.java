package com.hubrick.processor.impl;

import com.hubrick.processor.Processor;
import com.hubrick.repository.impl.DepartmentRepository;
import com.hubrick.repository.impl.EmployeeRepository;
import com.hubrick.service.ReportService;
import com.hubrick.writer.impl.CSVWriter;

import static com.hubrick.model.enums.ReportNameEnum.REPORT_95_INCOME_BY_DEPARTMENT;
import static com.hubrick.model.enums.ReportNameEnum.REPORT_AVG_INCOME_BY_AGE;
import static com.hubrick.model.enums.ReportNameEnum.REPORT_AVG_INCOME_BY_DEPARTMENT;
import static com.hubrick.model.enums.ReportNameEnum.REPORT_AVG_AGE_BY_DEPARTMENT;

/**
 * Created by eric.nascimento.
 */
public class CSVProcessor implements Processor {

    private ReportService reportService;
    private CSVWriter csvWriter;

    public CSVProcessor(ReportService reportService, CSVWriter csvWriter) {
        this.reportService = reportService;
        this.csvWriter = csvWriter;
    }

    @Override
    public boolean process() {

        csvWriter.write(REPORT_AVG_INCOME_BY_DEPARTMENT.getName(), reportService.getAverageIncomeByDepartment());
        csvWriter.write(REPORT_95_INCOME_BY_DEPARTMENT.getName(), reportService.getAverageIncome95ByDepartment());
        csvWriter.write(REPORT_AVG_INCOME_BY_AGE.getName(), reportService.getAverageIncomeByAgeFactorOfTen());
        csvWriter.write(REPORT_AVG_AGE_BY_DEPARTMENT.getName(), reportService.getAverageAgeByDepartment());
        return true;

    }

}
