<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
</head>
<body>
<h1>Prescription editing page</h1>
<p>Form for editing:</p>
<form:form modelAttribute="prescription" method="post" action="add_page" id="submitForm">
    <fieldset>
        <p>Type</p>
            <%--
                <form:label path="scratch.type.therapyType" for="typeSelectionList">Choose type of therapy:</form:label>
            --%>
        <form:select path="scratch.typeTherapy" id="typeSelectionList" name="typeOfTherapy">
            <optgroup label="Previous value:">
                <form:option value="${prescription.scratch.typeTherapy}">${prescription.scratch.typeTherapy}</form:option>
            </optgroup>
        </form:select>

        <form:select path="scratch.type.therapyName" id="medicineSelectionList" name="nameOfMedicine">
            <optgroup label="Previous value:">
                <form:option value="${prescription.scratch.typeTherapyName}">${prescription.scratch.typeTherapyName}</form:option>
            </optgroup>
            <optgroup label="Medicines:">
                <c:forEach items="${medicines}" var="medicine">
                    <form:option value="${medicine.name}">${medicine.name}</form:option>
                </c:forEach>
            </optgroup>
        </form:select>
        <p>Time Pattern</p>
        <form:label path="scratch.timePatternQuantity" for="quantityInTP">quantity in time pattern:</form:label>
        <form:input path="scratch.timePatternQuantity" type="text" id="quantityInTP" name="timePatternQuantity" placeholder="${prescription.scratch.timePatternQuantity}"/>
        at
        <form:select path="scratch.timePatternTimePeriod" id="timePeriodSelectionListInTP" name="timePatternTimePeriod">
            <form:option value="${prescription.scratch.timePatternTimePeriod}">${prescription.scratch.timePatternTimePeriod}</form:option>
            <c:forEach items="<%=TimePeriods.values()%>" var="timePeriod">
                <form:option value="${timePeriod}">${timePeriod}</form:option>
            </c:forEach>
        </form:select>
        <form:input path="scratch.timePatternAdditionalInformation" type="text" id="addInf" name="additionalInformation" placeholder="${prescription.scratch.timePatternAdditionalInformation}"/>

        <p>Period</p>
        <form:label path="scratch.periodsQuantity" for="quantityInP">quantity of periods:</form:label>
        <form:input path="scratch.periodsQuantity" type="text" id="quantityInP" name="timePeriodQuantity" placeholder="${prescription.scratch.periodsQuantity}"/>
        <form:select path="scratch.periodTimePeriod" id="timePeriodSelectionListInP" name="periodTimePeriod">
            <option selected value="${prescription.scratch.periodTimePeriod}">${prescription.scratch.periodTimePeriod}</option>
            <c:forEach items="<%=TimePeriods.values()%>" var="timePeriod">
                <form:option value="${timePeriod}">${timePeriod}</form:option>
            </c:forEach>
        </form:select>


        <p>Dose</p>
        <form:label path="scratch.doseDescription">dose:</form:label>
        <form:input path="scratch.doseDescription" type="text" id="doseInput" name="dose" placeholder="${prescription.scratch.doseDescription}"/>

    </fieldset>

    <footer>
        <input id="submitButton" type="submit" value="Submit"/>
    </footer>

    <script>
        function disableDose(){
            alert("dose disabled");
            var therapyType = document.querySelector('#typeSelectionList');
            var dose = document.querySelector('#doseInput');
            if (therapyType.value.toString() == 'MEDICINE'){
                dose.disabled = '';
            } else {
                dose.disabled = 'disabled';
                dose.value = null;
            }
        }
    </script>
</form:form>
</body>
</html>
