<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.noskov.school.enums.PatientStatus" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.noskov.school.persistent.PrescriptionPO" %>
<html>
<head>
    <title>Adding page</title>
</head>
<body>
<h1>Adding page</h1>
<form:form method="POST" action="add" modelAttribute="patient">
    <fieldset>
        <form:label path="firstName">Patient's first name:</form:label>
        <form:input path="firstName" placeholder="first name" /><br>
        <form:label path="lastName">Patient's last name:</form:label>
        <form:input path="lastName" placeholder="last name"/><br>
        <form:label path="diagnose">Patient's diagnose:</form:label>
        <form:input path="diagnose" placeholder="diagnose"/><br>
        <form:label path="socialNumber">Patient's social number:</form:label>
        <form:input path="socialNumber" placeholder="social number"/><br>

        <form:label path="status">Patient's status</form:label><br>
        <form:select path="status">
            <form:option value="<%=PatientStatus.TREATING%>"><%=PatientStatus.TREATING%></form:option>
            <form:option value="<%=PatientStatus.TREATED%>"><%=PatientStatus.TREATED%></form:option>
        </form:select>
    </fieldset>
    <footer>
        <input type="submit" value="Submit"/>
    </footer>
</form:form>
</body>
</html>
