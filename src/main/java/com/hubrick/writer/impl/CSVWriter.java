package com.hubrick.writer.impl;

import com.hubrick.exceptions.CSVWriteException;
import com.hubrick.model.ReportData;
import com.hubrick.writer.Writer;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVWriter implements Writer {

    @Override
    public boolean write(ReportData reportData) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean write(String fileName, ReportData reportData) {

        StringBuilder content = new StringBuilder(reportData.getHeader());
        reportData.getContent().forEach((key, value) -> content.append(key).append(",").append(value).append("\n"));

        try {
            Files.write(Paths.get(fileName + ".csv"), content.toString().getBytes());
            return true;
        } catch (Exception e) {
            throw new CSVWriteException(String.format("Error writing report %s ", fileName, e));
        }

    }

}
