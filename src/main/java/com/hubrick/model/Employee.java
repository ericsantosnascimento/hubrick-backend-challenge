package com.hubrick.model;

import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 4703655735897099572L;

    private Integer departmentId;
    private String name;
    private String gender;
    private Double income;
    private Integer age;

    public Employee(Integer departmentId, String name, String gender, Double income, Integer age) {
        this.departmentId = departmentId;
        this.name = name;
        this.gender = gender;
        this.income = income;
        this.age = age;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Double getIncome() {
        return income;
    }


    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", income=" + income +
                ", age=" + age +
                '}';
    }
}
