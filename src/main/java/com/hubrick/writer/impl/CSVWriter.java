package com.hubrick.writer.impl;

import com.hubrick.exceptions.CSVWriteException;
import com.hubrick.model.ReportData;
import com.hubrick.writer.Writer;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Responsible for write csv files.
 *
 * @author eric.nascimento
 */
public class CSVWriter implements Writer {

    private static CSVWriter instance;

    public static CSVWriter getInstance() {
        if (instance == null) {
            instance = new CSVWriter();
        }
        return instance;
    }

    private CSVWriter() {
        super();
    }

    @Override
    public boolean write(ReportData reportData) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean write(String fileName, ReportData reportData) {

        try {
            byte[] content = convertMapToBytes(reportData);
            Files.write(Paths.get(fileName + ".csv"), content);
            return true;
        } catch (Exception e) {
            throw new CSVWriteException(String.format("Error writing report %s ", fileName, e));
        }

    }

    private byte[] convertMapToBytes(ReportData reportData) {
        StringBuilder content = new StringBuilder(reportData.getHeader());
        reportData.getContent().forEach((key, value) -> content.append(key).append(",").append(value).append("\n"));
        return content.toString().getBytes();
    }

}
