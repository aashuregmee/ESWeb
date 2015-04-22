package com.esweb.domain;

/**
 * Created by asim on 4/20/15.
 */
public class Employee implements Comparable<Employee>{

    private int empId;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private double salary;
    private String department;

    public Employee() {


    }

    public Employee(int empId, String firstName, String lastName, String gender,
                            int age, double salary, String department) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        if(firstName == null)
            throw new NullPointerException();

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName == null)
            throw new NullPointerException();

        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {

        if(department == null)
            throw new NullPointerException();

        this.department = department;
    }

    public int compareTo(Employee e) {
        return empId - e.empId;
    }
}