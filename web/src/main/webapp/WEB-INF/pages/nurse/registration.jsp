<%@ page import="java.util.List" %>
<%@ page import="com.noskov.school.persistent.StaffPostPO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 12.02.2021
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nurse registration page</title>
</head>
<body>
<h1>Nurse registration form:</h1>
<c:if test="${usernameError!=null}">
    <h3>${usernameError}</h3>
</c:if>
<c:if test="${passwordError!=null}">
    <h3>${passwordError}</h3>
</c:if>
<form:form method="post" action="registration" modelAttribute="staff">
    <fieldset>
        <form:label path="firstName">First name:</form:label>
        <form:input path="firstName" placeholder="enter your first name" /><br>
        <form:label path="lastName">Last name:</form:label>
        <form:input path="lastName" placeholder="enter your last name"/><br>
        <form:label path="email">Email:</form:label>
        <form:input path="email" placeholder="enter your email"/><br>
        <form:label path="password">Password:</form:label>
        <form:password path="password" placeholder="password"/><br>
        <form:label path="passwordConfirm">Confirm your password:</form:label>
        <form:password path="passwordConfirm" placeholder="password"/><br>
    </fieldset>
    <footer>
        <input type="submit" value="Submit"/>
    </footer>
</form:form>

</body>
</html>
