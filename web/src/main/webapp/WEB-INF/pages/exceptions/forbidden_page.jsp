<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 23.02.2021
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Access denied</title>
</head>
<body>
<h1>You have not rights to forward this view,
    if it seems wrong to you, please call to supports team:</h1>
<h2>Telephone number: +7-921-555-55-55</h2>
<h3>You may redirect to your personal cabinet/welcome page:</h3>
<a href="<c:url value="/"/>">redirect to my cabinet/welcome page</a>
</body>
</html>
