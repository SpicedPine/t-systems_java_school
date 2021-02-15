<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 22.01.2021
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>REHA final task</title>
</head>
<body>
    <h1>REHA documentation application</h1>
    <sec:authorize access="!isAuthenticated()">
        <h2><a href="<c:url value='/login'/>">Log in</a></h2>
        <h2><a href="<c:url value='physician/registration'/>">Physician's registration page</a></h2>
        <h2><a href="<c:url value='nurse/registration'/>">Nurse's registration page</a></h2>
    </sec:authorize>
    <%--<h2><a href="<c:url value='/login'/>">Log in</a></h2>
    <h2><a href="<c:url value='/physician/registration'/>">Physician's registration page</a></h2>
    <h2><a href="<c:url value='/nurse/registration'/>">Nurse's registration page</a></h2>
    --%>
    <sec:authorize access="isAuthenticated()">
        <h2><a href="<c:url value='/logout'/>">Log out</a></h2>
    </sec:authorize>
    <sec:authorize access="hasRole('PHYSICIAN')">
        <h2><a href="<c:url value='patient/'/>">Patients page (only for physicians)</a></h2>
    </sec:authorize>
    <sec:authorize access="hasRole('NURSE')">
        <h2><a href="<c:url value='/event/' />">Events page (only for nurses)</a></h2>
    </sec:authorize>
</body>
</html>
