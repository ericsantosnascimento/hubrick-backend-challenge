package com.hubrick.model;

/**
 * Represents the department entity, to build a instance use {@link DepartmentBuilder}
 *
 * @author eric.nascimento
 */
public class Department {

    private static Integer idAutoIncrement = 1;

    private Integer id;
    private String description;

    private Department(String description) {
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

    public static class DepartmentBuilder {

        private String description;

        public Department.DepartmentBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public Department build() {
            return new Department(description);
        }

    }
}
