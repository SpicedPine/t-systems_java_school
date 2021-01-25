<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding page</title>
</head>
<body>
<h1>Adding page</h1>
<form:form method="POST" action="add" id="submitButton">
    <label for="firstName">Patient's first name:</label>
    <input id="firstName" type="text" placeholder="first name"><br>
    <label for="lastName">Patient's last name:</label>
    <input id="lastName" type="text" placeholder="last name"><br>
    <label for="diagnose">Patient's diagnose:</label>
    <input id="diagnose" type="text" placeholder="diagnose"><br>
    <label for="socialNumber">Patient's social number:</label>
    <input id="socialNumber" type="text" placeholder="social number"><br>
    <select id="status">
        <option>treating</option>
        <option>treated</option>
    </select>
    <label for="status">Patient's status</label>
    <input type="submit" value="Submit228"/>

</form:form>
</body>
</html>
