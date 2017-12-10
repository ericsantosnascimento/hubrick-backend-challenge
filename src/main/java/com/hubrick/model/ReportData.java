package com.hubrick.model;

import java.util.Map;

public class ReportData<T,E> {

    private String header;
    private Map<T, E> content;

    public ReportData(String header, Map<T, E> content) {
        this.header = header;
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public Map<T, E> getContent() {
        return content;
    }
}
