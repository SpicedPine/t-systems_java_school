<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <th>Physician</th>
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
                <td>${patient.physician}</td>
                <td>${patient.status}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
