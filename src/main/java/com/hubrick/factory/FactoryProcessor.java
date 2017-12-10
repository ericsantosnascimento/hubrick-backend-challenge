package com.hubrick.factory;

import com.hubrick.factory.type.FactoryType;
import com.hubrick.processor.AbstractProcessor;
import com.hubrick.processor.CsvProcessor;
import com.hubrick.repository.impl.DepartmentRepository;
import com.hubrick.repository.impl.EmployeeRepository;
import com.hubrick.service.ReportService;

/**
 * Provide type to get the processor implementation.
 *
 * Created by eric.nascimento.
 */
public class FactoryProcessor {

    public static AbstractProcessor getProcessor(FactoryType.Type type) {

        if (type == FactoryType.Type.CSV) {
            return new CsvProcessor();
        } else {
            throw new UnsupportedOperationException();
        }

    }
}
