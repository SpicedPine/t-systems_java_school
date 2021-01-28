<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<textarea type="text" id="whyCancelled" placeholder="Enter the reason of cancelling">

</textarea><br>

<a href="<c:url value="/event/"/>">Submit and return to events</a>
</body>
</html>
