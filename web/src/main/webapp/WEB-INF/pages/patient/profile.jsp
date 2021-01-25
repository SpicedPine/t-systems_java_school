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
<button id="getPrescriptions" type="button" onclick="toPrescriptions.js">Prescriptions</button>
<button id="addPrescription" type="button" onclick="addPrescription.js">Add prescription</button>
</body>
</html>
