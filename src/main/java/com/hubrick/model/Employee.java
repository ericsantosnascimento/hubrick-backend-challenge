package com.hubrick.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Employee implements Serializable {

    private static Integer idAutoIncrement = 1;

    private Integer id;
    private Integer departmentId;
    private String name;
    private String gender;
    private BigDecimal income;
    private Integer age;

    public Employee(Integer departmentId, String name, String gender, BigDecimal income, Integer age) {
        this.id = idAutoIncrement++;
        this.departmentId = departmentId;
        this.name = name;
        this.gender = gender;
        this.income = income;
        this.age = age;
    }

    public Integer getId() {
        return id;
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

    public BigDecimal getIncome() {
        return income.setScale(2, RoundingMode.CEILING);
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
