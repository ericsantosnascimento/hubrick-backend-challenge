package com.hubrick.model.enums;

public enum ReportHeaderEnum {

    REPORT_AVERAGE_AGE_DEPARTMENT_HEADER("department, average age \n"),
    REPORT_AVERAGE_INCOME_DEPARTMENT_HEADER("department, average income \n"),
    REPORT_INCOME_AVERAGE_AGE_HEADER("age, average income \n");

    private final String header;

    ReportHeaderEnum(String header) {
        this.header = header;
    }

    public String getHeader() {
        return this.header;
    }
}
