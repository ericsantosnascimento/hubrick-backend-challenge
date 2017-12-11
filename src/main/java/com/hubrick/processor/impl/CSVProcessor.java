package com.hubrick.processor.impl;

import com.hubrick.processor.Processor;
import com.hubrick.service.ReportService;
import com.hubrick.writer.impl.CSVWriter;

import static com.hubrick.model.enums.ReportNameEnum.REPORT_95_INCOME_BY_DEPARTMENT;
import static com.hubrick.model.enums.ReportNameEnum.REPORT_AVG_AGE_BY_DEPARTMENT;
import static com.hubrick.model.enums.ReportNameEnum.REPORT_AVG_INCOME_BY_AGE;
import static com.hubrick.model.enums.ReportNameEnum.REPORT_AVG_INCOME_BY_DEPARTMENT;

/**
 * Process available Csv files
 *
 * @author eric.nascimento.
 */
public class CSVProcessor implements Processor {

    private static CSVProcessor instance;

    private ReportService reportService;
    private CSVWriter csvWriter;

    public static CSVProcessor getInstance(ReportService reportService, CSVWriter csvWriter) {
        if (instance == null) {
            instance = new CSVProcessor(reportService, csvWriter);
        }
        return instance;
    }

    private CSVProcessor(ReportService reportService, CSVWriter csvWriter) {
        this.reportService = reportService;
        this.csvWriter = csvWriter;
    }

    /**
     * Consolidating reports and writing data into files, return success in case of success, any exception will during process will throw runtime
     * exceptions
     *
     * @return boolean success
     */
    @Override
    public boolean process() {

        csvWriter.write(REPORT_AVG_INCOME_BY_DEPARTMENT.getName(), reportService.getAverageIncomeByDepartment());
        csvWriter.write(REPORT_95_INCOME_BY_DEPARTMENT.getName(), reportService.getAverageIncome95ByDepartment());
        csvWriter.write(REPORT_AVG_INCOME_BY_AGE.getName(), reportService.getAverageIncomeByAgeFactorOfTen());
        csvWriter.write(REPORT_AVG_AGE_BY_DEPARTMENT.getName(), reportService.getAverageAgeByDepartment());

        return true;

    }

}
