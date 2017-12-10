package com.hubrick.writer.impl;

import com.hubrick.exceptions.CSVWriteException;
import com.hubrick.model.ReportData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;

@RunWith(MockitoJUnitRunner.class)
public class CSVWriterTest {

    private CSVWriter csvWriter;

    @Before
    public void setUp() throws Exception {
        this.csvWriter = CSVWriter.getInstance();
    }

    @Test
    public void write() {

        Map<String, String> content = new HashMap<>();
        content.put("test", "test");
        ReportData<String, String> reportData = new ReportData<>("header", content);
        boolean success = csvWriter.write("teste", reportData);
        Assert.assertThat(success, is(true));
    }

    @Test(expected = CSVWriteException.class)
    public void writeWithNullReportData() {
        csvWriter.write("teste", null);
    }

    @After
    public void clean() {
        File file = new File("teste.csv");
        file.delete();
    }

}