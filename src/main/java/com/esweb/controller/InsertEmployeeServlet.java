package com.esweb.controller;

import com.esweb.commons.ESWebConstants;
import com.esweb.domain.Employee;
import com.esweb.services.EmployeeService;
import org.elasticsearch.client.transport.NoNodeAvailableException;
import org.elasticsearch.indices.IndexMissingException;

import java.io.IOException;

/**
 * Created by asim on 4/21/15.
 */
public class InsertEmployeeServlet extends javax.servlet.http.HttpServlet {
    private EmployeeService employeeService = new EmployeeService();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        boolean inserted = false;
        String message = ESWebConstants.ERROR_MSG;
        int id;
        try {
            id = Integer.parseInt(request.getParameter(ESWebConstants.ID));
            String firstName = request.getParameter(ESWebConstants.FIRST_NAME);
            String lastName = request.getParameter(ESWebConstants.LAST_NAME);
            String gender = request.getParameter(ESWebConstants.GENDER);
            int age = Integer.parseInt(request.getParameter(ESWebConstants.AGE));
            double salary = Double.parseDouble(request.getParameter(ESWebConstants.SALARY));
            String department = request.getParameter(ESWebConstants.DEPARTMENT);

            Employee employee = new Employee(id, firstName, lastName, gender, age, salary, department);

            inserted = employeeService.insertEmployee(employee);
            request.setAttribute("maxId", id + 1);
        }catch (NoNodeAvailableException e){

            message = ESWebConstants.ES_START_MSG;
        }catch (NumberFormatException e){

            e.printStackTrace();
        }
        request.setAttribute("message", message);
        request.setAttribute("inserted", inserted);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String message = "";
        try {
            int maxId = employeeService.getMaxId();
            request.setAttribute("maxId", maxId + 1);
        }catch (NoNodeAvailableException e){

            message = ESWebConstants.ES_START_MSG;
        }catch (IndexMissingException e){

            request.setAttribute("maxId", 1);
        }

        request.setAttribute("message", message);
        request.setAttribute("searched", false);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
