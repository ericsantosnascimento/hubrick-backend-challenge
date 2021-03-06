package com.hubrick.writer;

import com.hubrick.model.ReportData;

/**
 * Interface for writing data.
 */
public interface Writer {

    boolean write(ReportData reportData);

    boolean write(String fileName, ReportData reportData);
}
