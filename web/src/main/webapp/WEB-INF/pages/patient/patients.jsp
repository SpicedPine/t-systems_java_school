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
                <td><button id="release" onclick="releasePatient.js">release</button></td>
                <td><button id="peek" onclick="toProfile.js">profile</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
