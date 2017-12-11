package com.hubrick.model.enums;

/**
 * Holds the current reports name
 */
public enum ReportNameEnum {

    REPORT_95_INCOME_BY_DEPARTMENT("income-95-by-department"),
    REPORT_AVG_INCOME_BY_DEPARTMENT("income-by-department"),
    REPORT_AVG_INCOME_BY_AGE("income-average-by-age-range"),
    REPORT_AVG_AGE_BY_DEPARTMENT("employee-age-by-department");

    private final String name;

    ReportNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
