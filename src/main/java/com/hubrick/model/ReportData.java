package com.hubrick.model;

import java.util.Map;

/**
 * Represents the report itself, it's a type class T and E will be map type
 *
 * @param <T> map key type
 * @param <E> map value type
 *
 * @author eric.nascimento
 */
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
