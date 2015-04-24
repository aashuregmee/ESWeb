<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: asim
  Date: 4/21/15
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deerwalk Services Pvt. Ltd.</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <style type="text/css">
      #customers {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        width: 100%;
        border-collapse: collapse;
      }

      #customers td, #customers th {
        font-size: 1em;
        border: 1px solid #de8819;
        padding: 3px 7px 2px 7px;
      }

      #customers th {
        font-size: 1.1em;
        text-align: left;
        padding-top: 5px;
        padding-bottom: 4px;
        background-color: #de8819;
        color: #ffffff;
      }

      #customers tr.alt td {
        color: #000000;
        background-color: #EAF2D3;
      }
    </style>
</head>
<body>
<div class="form-style-2-heading">
    <a href="/dws"> DWS Employee Database </a>
</div>
<table id = "customers">
      <thead>
          <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Gender</th>
            <th>Age</th>
            <th>Salary</th>
            <th>Department</th>
          </tr>
      </thead>
      <tbody>
        <c:set var="total" value="${0}"/>
        <c:forEach items="${employees}" var="employee">
            <tr>
              <td>${employee.empId}</td>
              <td>${employee.firstName}</td>
              <td>${employee.lastName}</td>
              <td>${employee.gender}</td>
              <td>${employee.age}</td>
              <td>${employee.salary}</td>
              <td>${employee.department}</td>
            </tr>
            <c:set var="total" value="${total + employee.salary}"/>
        </c:forEach>
            <tr>
                <td><strong>Total</strong></td>
                <td>--</td>
                <td>--</td>
                <td>--</td>
                <td>--</td>
                <td>${total}</td>
                <td>--</td>
            </tr>
      </tbody>
</table>
</body>
</html>