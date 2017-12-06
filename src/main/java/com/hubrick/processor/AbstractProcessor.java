package com.hubrick.processor;

import com.hubrick.repository.impl.DepartmentRepository;
import com.hubrick.repository.impl.EmployeeRepository;
import com.hubrick.service.ReportService;

import java.util.Map;

/**
 * Created by eric.nascimento.
 */
public abstract class AbstractProcessor {

    protected ReportService reportService;

    public AbstractProcessor(ReportService reportService){
        this.reportService = new ReportService(new EmployeeRepository(), new DepartmentRepository());
    }

    public boolean process() {

        try {

            Map<Integer, Double> incomeByDepartment = reportService.getIncomeByDepartment();
            write("income-by-department", incomeByDepartment);

            Map<Integer, Double> income95ByDepartment = reportService.getIncome95ByDepartment();
            write("income-95-by-department", income95ByDepartment);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected abstract boolean write(String fileName, Map<Integer, Double> report);

}
