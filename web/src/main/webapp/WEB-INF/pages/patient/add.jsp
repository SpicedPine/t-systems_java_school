<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding page</title>
</head>
<body>
<h1>Adding page</h1>
<form:form modelAttribute="patient" method="POST" action="add" id="submitButton">
    <form:label path="firstName">Patient's first name:</form:label>
    <form:input path="firstName" type="text" placeholder="first name"/><br>
    <form:label path="lastName">Patient's last name:</form:label>
    <form:input path="lastName" type="text" placeholder="last name"/><br>
    <form:label path="diagnose">Patient's diagnose:</form:label>
    <form:input path="diagnose" type="text" placeholder="diagnose"/><br>
    <form:label path="socialNumber">Patient's social number:</form:label>
    <form:input path="socialNumber" type="text" placeholder="social number"/><br>

    <form:label path="status">Patient's status</form:label><br>
    <form:select path="status">
        <form:option value="treating">treating</form:option>
        <form:option value="treated">treated</form:option>
    </form:select>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
