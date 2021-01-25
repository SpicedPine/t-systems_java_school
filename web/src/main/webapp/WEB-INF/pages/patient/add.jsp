
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding page</title>
</head>
<body>
<h1>Adding page</h1>
<form>
    <label for="firstName">Patient's first name:</label>
    <input id="firstName" type="text" placeholder="first name"><br>
    <label for="lastName">Patient's last name:</label>
    <input id="lastName" type="text" placeholder="last name"><br>
    <label for="diagnose">Patient's diagnose:</label>
    <input id="diagnose" type="text" placeholder="diagnose"><br>
    <label for="socialNumber">Patient's social number:</label>
    <input id="socialNumber" type="text" placeholder="social number"><br>
    <select id="status">
        <option>treating</option>
        <option>treated</option>
    </select><label for="status">Patient's status</label>
    <button id="submitButton" type="submit">Submit</button>
</form>
</body>
</html>
