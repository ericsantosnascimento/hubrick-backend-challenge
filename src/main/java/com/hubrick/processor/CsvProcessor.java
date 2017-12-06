package com.hubrick.processor;

import com.hubrick.service.ReportService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class CsvProcessor extends AbstractProcessor {

    public CsvProcessor(ReportService reportService) {
        super(reportService);
    }

    @Override
    protected boolean write(String fileName, Map<Integer, Double> report) {

        StringBuilder content = new StringBuilder("department,average income \n");
        report.forEach((key, value) -> content.append(key).append(",").append(value).append("\n"));

        try {
            Files.write(Paths.get(fileName + ".csv"), content.toString().getBytes());
            return true;
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        }

    }

}
