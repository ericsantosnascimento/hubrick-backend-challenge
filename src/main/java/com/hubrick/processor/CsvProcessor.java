package com.hubrick.processor;

import com.hubrick.model.ReportData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvProcessor extends AbstractProcessor {

    @Override
    protected boolean write(String fileName, ReportData reportData) {

        StringBuilder content = new StringBuilder(reportData.getHeader());
        reportData.getContent().forEach((key, value) -> content.append(key).append(",").append(value).append("\n"));

        try {
            Files.write(Paths.get(fileName + ".csv"), content.toString().getBytes());
            return true;
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        }

    }

}
