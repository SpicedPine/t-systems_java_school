<%@ page import="com.noskov.school.enums.TherapyType" %>
<%@ page import="com.noskov.school.enums.TimePeriods" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 26.01.2021
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add prescription</title>
</head>
<body>
<h1>Page for prescription adding</h1>
<form:form modelAttribute="prescription" method="post" action="add_page" id="submitForm">
    <fieldset>
    <p>Type</p>

    <form:select path="scratch.typeTherapy" id="typeSelectionList" name="typeOfTherapy" onchange="disableDose()">
        <c:forEach items="<%=TherapyType.values()%>" var="therapyType">
            <form:option value="${therapyType}" id="${therapyType}">
                ${therapyType.toString()}
            </form:option>
        </c:forEach>
    </form:select>

    <form:select path="scratch.type.therapyName" id="medicineSelectionList" name="nameOfMedicine">
        <optgroup label="Medicines:">
            <c:forEach items="${medicines}" var="medicine">
                <form:option value="${medicine.name}">${medicine.name}</form:option>
            </c:forEach>
        </optgroup>
        <optgroup label="Procedures:">
            <c:forEach items="${procedures}" var="procedure">
                <form:option value="${procedure.name}">${procedure.name}</form:option>
            </c:forEach>
        </optgroup>
    </form:select>
    <p>Time Pattern</p>
    <form:label path="scratch.timePatternQuantity" for="quantityInTP">quantity in time pattern:</form:label>
    <form:input path="scratch.timePatternQuantity" type="text" id="quantityInTP" name="timePatternQuantity" placeholder="Enter time pattern quantity"/>
    at
    <form:select path="scratch.timePatternTimePeriod" id="timePeriodSelectionListInTP" name="timePatternTimePeriod" onchange="disablePeriodTimePeriod()">
        <c:forEach items="<%=TimePeriods.values()%>" var="timePeriod">
            <form:option value="${timePeriod}">${timePeriod}</form:option>
        </c:forEach>
    </form:select>
    <form:input path="scratch.timePatternAdditionalInformation" type="text" id="addInf" name="additionalInformation" placeholder="Enter additional information"/>

    <p>Period</p>
    <form:label path="scratch.periodsQuantity" for="quantityInP">quantity of periods:</form:label>
    <form:input path="scratch.periodsQuantity" type="text" id="quantityInP" name="timePeriodQuantity" placeholder="Enter quantity of periods"/>
    <form:select path="scratch.periodTimePeriod" id="timePeriodSelectionListInP" name="periodTimePeriod">
        <c:forEach items="<%=TimePeriods.values()%>" var="timePeriod">
            <form:option value="${timePeriod}">${timePeriod}</form:option>
        </c:forEach>
    </form:select>


    <c:if test="true">
        <p>Dose</p>
        <form:label path="scratch.doseDescription" for="doseInput">dose:</form:label>
        <form:input path="scratch.doseDescription" type="text" id="doseInput" name="dose" placeholder="Enter dose of medicine"/>
    </c:if>
    </fieldset>

    <footer>
        <input id="submitButton" type="submit" value="Submit"/>
    </footer>

    <script>
        function added(){
            alert("prescription added");
        }
    </script>
    <script>
        function disableDose(){
            var therapyType = document.querySelector('#typeSelectionList');
            var dose = document.querySelector('#doseInput');
            if (therapyType.value.toString() == 'MEDICINE'){
                dose.disabled = '';
            } else {
                dose.disabled = 'disabled';
                alert("dose disabled");
                dose.value = null;
            }
        }
    </script>

    <script>
        function disablePeriodTimePeriod(){
            alert("period timePeriod disabled");
            var timePatternTimePeriod = document.querySelector('#timePeriodSelectionListInTP');
            var periodTimePeriod = document.querySelector('#timePeriodSelectionListInP');
            if (timePatternTimePeriod.value.toString() == 'WEEK'){
                periodTimePeriod.childNodes[0].disabled = 'disabled';
            } else {
            }
        }
    </script>
</form:form>
</body>
</html>
