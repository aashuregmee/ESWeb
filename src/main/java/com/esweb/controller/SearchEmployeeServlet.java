package com.esweb.controller;

import com.esweb.commons.ESWebConstants;
import com.esweb.domain.Employee;
import com.esweb.services.EmployeeService;
import org.elasticsearch.client.transport.NoNodeAvailableException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by asim on 4/21/15.
 */
public class SearchEmployeeServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        EmployeeService employeeService = new EmployeeService();
        int id;
        int age;
        double salary;
        try {
            id = Integer.parseInt(request.getParameter(ESWebConstants.ID));
        }catch (NumberFormatException e){
            id = 0;
        }
        String firstName = request.getParameter(ESWebConstants.FIRST_NAME);
        String lastName = request.getParameter(ESWebConstants.LAST_NAME);
        String gender = request.getParameter(ESWebConstants.GENDER);
        try {
            age = Integer.parseInt(request.getParameter(ESWebConstants.AGE));
        }catch(NumberFormatException e){
            age = 0;
        }
        try {
            salary = Double.parseDouble(request.getParameter(ESWebConstants.SALARY));
        }catch(NumberFormatException e){
            salary = 0;
        }
        String department = request.getParameter(ESWebConstants.DEPARTMENT);

        try {
            List<Employee> employees = employeeService.search(id, firstName, lastName, gender, age, salary, department);
            Collections.sort(employees);
            request.setAttribute("employees", employees);
            request.getRequestDispatcher("/searchResult.jsp").forward(request, response);
        }catch(NoNodeAvailableException e){

            request.setAttribute("searched", false);
            request.setAttribute("message", ESWebConstants.ES_START_MSG);
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }
    }
}
