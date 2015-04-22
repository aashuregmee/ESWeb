<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Deerwalk Services Pvt. Ltd.</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
    <div class="form-style-2-heading">
        <a href="/dws"> DWS Employee Database </a>
    </div>
    <div id = "message">
        <c:if test="${inserted}">
            <p>Employee has been successfully inserted!!</p>
        </c:if>
        <c:if test="${inserted == false}">
            <p>${message}</p>
        </c:if>
        <c:if test="${searched == false}">
            <p>${message}</p>
        </c:if>
    </div>
    <div class="form-style-2">
        <form method="post">
            <label for = "id">Id: </label><input type="text" name="id" class="input-field" value="${maxId}"><br>
            <label for = "firstName">First Name: </label><input type="text" name="firstName" class="input-field"><br>
            <label for = "lastName">Last Name: </label><input type="text" name="lastName" class="input-field"><br>
            <label for = "gender">Gender: </label>
            <select name="gender" class="select-field">
                <option></option>
                <option>Male</option>
                <option>Female</option>
            </select><br>
            <label for = "age">Age: </label><input type="number" name="age" class="input-field"><br>
            <label for = "salary">Salary: </label><input type="text" name="salary" class="input-field"><br>
            <label for="department">Department: </label> <input type="text" name="department" class="input-field">

            <br>
            <input type="submit" formaction="/search" value="Search">
            <input type="submit" formaction="/dws" value="Insert">
        </form>
    </div>
</div>
</body>
</html>