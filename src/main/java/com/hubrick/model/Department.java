package com.hubrick.model;

public class Department {

    private static Integer idAutoIncrement = 1;

    private Integer id;
    private String description;

    public Department(String description) {
        this.description = description;
        this.id = idAutoIncrement++;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
