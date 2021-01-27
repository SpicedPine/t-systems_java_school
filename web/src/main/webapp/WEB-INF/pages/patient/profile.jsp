<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 22.01.2021
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient Profile</title>
</head>
<body>
<h1>${patient.firstName} ${patient.lastName} profile</h1>

<table>
    <thead>
    <tr>
        <th>Procedure or medicine name</th>
        <th>Prescription</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${patient.prescriptionList}" var="prescription">
        <tr>
            <td>${prescription.prescriptionType.name}</td>
            <td>${prescription.formedPrescription}</td>
            <td><a href=<c:url value="/patient/profile/${prescription.id}"/>>edit prescription</a></td>
            <td><a href=<c:url value="/patient/${patient.id}/cancel/${prescription.id}"/>>cancel prescription</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="<c:url value="/patient/${patient.id}/add_prescription"/>">Add prescription</a>

</body>
</html>
