<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 12.02.2021
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logging page</title>
</head>
<body>
<form:form method="post" action="login" modelAttribute="staff">
    <form:label path="email">Enter your email:</form:label>
    <form:input path="email" placeholder="email" /><br>
    <form:label path="password">Enter your password:</form:label>
    <form:input path="password" placeholder="password"/><br>
    <footer>
        <input type="submit" value="Log in"/>
    </footer>
</form:form>
</body>
</html>
