package com.hubrick.processor;

import com.hubrick.model.Department;
import com.hubrick.model.Employee;
import com.hubrick.repository.impl.DepartmentRepository;
import com.hubrick.repository.impl.EmployeeRepository;

import java.util.List;

/**
 * Created by eric.nascimento.
 */
public abstract class AbstractProcessor {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    public AbstractProcessor(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository){
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public boolean process() {

        try {

            List<Department> departments = departmentRepository.findAll();
            List<Employee> employees = employeeRepository.findAll();
            return write(departments, employees);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected abstract boolean write(List<Department> departments, List<Employee> employees);

}
