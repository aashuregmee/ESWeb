package com.esweb.services;

import com.esweb.domain.Employee;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/**
 * Created by asim on 4/20/15.
 */

public class EmployeeServiceTest extends TestCase{

    private EmployeeService employeeService = new EmployeeService();

    @Test
    public void testInsertEmployee(){

        Employee employee = new Employee();

        employee.setEmpId(2);
        employee.setFirstName("Ashish");
        employee.setLastName("Adhikari");
        employee.setGender("Male");
        employee.setAge(1);
        employee.setSalary(20000);
        employee.setDepartment("KMH");

        assertEquals(true, employeeService.insertEmployee(employee));
    }

    @Test
    public void testSearch(){

/*        List<Employee> employees = employeeService.search(1, null, null, null, 0, 0, 0, 0, null);

        printAllEmployeeDetails(employees);

        employees = employeeService.search(0, "As", null, null, 0, 0, 0, 0, null);

        printAllEmployeeDetails(employees);

        employees = employeeService.search(0, null, "R", null, 0, 0, 0, 0, null);

        printAllEmployeeDetails(employees);

        employees = employeeService.search(0, null, null, "Male", 0, 0, 0, 0, null);

        printAllEmployeeDetails(employees);

        employees = employeeService.search(0, null, null, null, 19, 0, 0, 0, null);

        printAllEmployeeDetails(employees);

        employees = employeeService.search(0, null, null, null, 0, 2, 0, 0, null);

        printAllEmployeeDetails(employees);

        employees = employeeService.search(0, null, null, null, 0, 0, 1, 0, null);

        printAllEmployeeDetails(employees);

        employees = employeeService.search(0, null, null, null, 0, 0, 0, 20000, null);

        printAllEmployeeDetails(employees);

        employees = employeeService.search(0, null, null, null, 0, 0, 0, 0, "KMH");

        printAllEmployeeDetails(employees);

        //Combination Queries along with match_phrase_prefix
        System.out.println("Combination Queries along with match_phrase_prefix");

        employees = employeeService.search(1, "As", null, null, 0, 0, 0, 0, null);
        printAllEmployeeDetails(employees);*/

    }

    private void printAllEmployeeDetails(List<Employee> employees){

        System.out.println("The total no. of employees is " + employees.size());
        for(Employee employee : employees){

            System.out.println(employee.getFirstName());
            System.out.println(employee.getLastName());
            System.out.println(employee.getGender());
            System.out.println(employee.getSalary());
            System.out.println("----------------------------------------------------------------------------------");
        }
    }

}
