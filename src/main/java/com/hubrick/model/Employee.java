package com.hubrick.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents the employee entity, to build a instance use {@link Employee.EmployeeBuilder}
 *
 * @author eric.nascimento
 */
public class Employee implements Serializable {

    private static Integer idAutoIncrement = 1;

    private final Integer id;
    private final Integer departmentId;
    private final String name;
    private final String gender;
    private final BigDecimal income;
    private final Integer age;

    private Employee(Integer departmentId, String name, String gender, BigDecimal income, Integer age) {
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

    public static class EmployeeBuilder {

        private Integer departmentId;
        private String name;
        private String gender;
        private BigDecimal income;
        private Integer age;

        public EmployeeBuilder departmentId(final Integer departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public EmployeeBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public EmployeeBuilder gender(final String gender) {
            this.gender = gender;
            return this;
        }

        public EmployeeBuilder income(final BigDecimal income) {
            this.income = income;
            return this;
        }

        public EmployeeBuilder age(final Integer age) {
            this.age = age;
            return this;
        }

        public Employee build() {
            return new Employee(departmentId, name, gender, income, age);
        }

    }
}
