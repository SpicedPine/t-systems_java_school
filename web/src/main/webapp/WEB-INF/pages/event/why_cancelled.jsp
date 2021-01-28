<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 28.01.2021
  Time: 4:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Why event was cancelled?</h1>

<form:form modelAttribute="event" method="post" action="changeToCancelled">
    <form:textarea path="reasonToCancel" id="cancelReason"/>
    <input type="submit" value="Submit"/>
</form:form>

</body>
</html>
