<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>All patients</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Diagnose</th>
                <th>Social number</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${patients}" var="patient">
            <tr>
                <td>${patient.firstName}</td>
                <td>${patient.lastName}</td>
                <td>${patient.diagnose}</td>
                <td>${patient.socialNumber}</td>
                <td>${patient.status}</td>
                <td><a href="<c:url value='/patient/${patient.id}/release'/>">release</a></td>
                <td><a href="<c:url value="/patient/${patient.id}"/>">profile</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p>
        <a href="<c:url value='/patient/add'/>">Add new patient</a>
    </p>
    <p>
        <a href="<c:url value='/'/>">Back to personal cabinet</a>
    </p>
</body>
</html>
