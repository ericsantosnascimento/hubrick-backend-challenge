package com.hubrick.processor;

import com.hubrick.model.ReportData;
import com.hubrick.repository.impl.DepartmentRepository;
import com.hubrick.repository.impl.EmployeeRepository;
import com.hubrick.service.ReportService;

/**
 * Created by eric.nascimento.
 */
public abstract class AbstractProcessor {

    private ReportService reportService;

    protected AbstractProcessor(){
        this.reportService = new ReportService(new EmployeeRepository(), new DepartmentRepository());
    }

    public boolean process() {

        try {

            write("income-by-department", reportService.getAverageIncomeByDepartment());
            write("income-95-by-department", reportService.getAverageIncome95ByDepartment());
            write("income-average-by-age-range", reportService.getAverageIncomeByAgeFactorOfTen());
            write("employee-age-by-department", reportService.getAverageAgeByDepartment());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param fileName
     * @param reportData
     * @return boolean successful or not
     */
    protected abstract boolean write(String fileName, ReportData reportData);

}
