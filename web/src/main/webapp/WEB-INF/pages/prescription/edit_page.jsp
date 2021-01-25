<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 22.01.2021
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.noskov.school.enums.TherapyType" %>
<%@ page import="com.noskov.school.enums.TimePeriods" %>
<html>
<head>
    <title>Page for prescription editing</title>
    <script type="text/javascript">

        function changeFunc() {
            var typeSelectionList = document.getElementById("typeSelectionList");
            var selectedValue = typeSelectionList.options[typeSelectionList.selectedIndex].value;
            alert(selectedValue);
        }

    </script>
</head>
<body>
<h1>Prescription editing page</h1>
<form>
    <p><b>Form fot generating prescription:</b></p>
    <p>Type</p>
    <select id="typeSelectionList" name="typeOfTherapy" onchange="changeFunc();">
        <c:forEach items="<%=TherapyType.values()%>" var="therapyType">
            <option value="${therapyType}">${therapyType.toString()}</option>
        </c:forEach>
    </select>
    <c:choose>
        <c:when test="selectedValue==<%=TherapyType.MEDICINE.toString()%>">
            <select id="medicineSelectionList" name="nameOfMedicine">
                <c:forEach items="${medicines}" var="medicine">
                    <option value="${medicine}">${medicine.name}</option>
                </c:forEach>
            </select>
        </c:when>

        <c:when test="selectedValue==<%=TherapyType.PROCEDURE.toString()%>">
            <select id="procedureSelectionList" name="nameOfProcedure">
                <c:forEach items="${procedures}" var="procedure">
                    <option value="${procedure}">${procedure.name}</option>
                </c:forEach>
            </select>
        </c:when>

        <c:otherwise>
            Please, select type of Therapy
        </c:otherwise>
    </c:choose>

    <p>Time Pattern</p>
    <input type="text" id="quantityInTP" name="timePatternQuantity" placeholder="enter quantity in period">
    <label for="quantityInTP">quantity in time pattern:</label>
    at
    <select id="timePeriodSelectionListInTP" name="timePatternTimePeriod">
        <c:forEach items="<%=TimePeriods.values()%>" var="timePeriod">
            <option value=${timePeriod}>${timePeriod}</option>
        </c:forEach>
    </select>
    <input type="text" id="addInf" name="additionalInformation" placeholder="enter additional information">

    <p>Period</p>
    <input type="text" id="quantityInP" name="timePeriodQuantity" placeholder="enter quantity of periods">
    <label for="quantityInP">quantity of periods:</label>
    <select id="timePeriodSelectionListInP" name="periodTimePeriod">
        <c:forEach items="<%=TimePeriods.values()%>" var="timePeriod">
            <option value=${timePeriod}>${timePeriod}</option>
        </c:forEach>
    </select>

    <c:if test="selectedValue==<%=TherapyType.MEDICINE%>">
        <p>Dose</p>
        <input type="text" id="doseInput" name="dose" placeholder="enter ammount or ratio of standart dose">
        <label for="doseInput">dose:</label>
    </c:if>

</form>
</body>
</html>
