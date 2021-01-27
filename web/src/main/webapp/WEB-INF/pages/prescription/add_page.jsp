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
    <title>Title</title>
    <script type="text/javascript">
        function isProcedure() {
            var typeSelectionList = document.getElementById("typeSelectionList");
            var selectedValue = typeSelectionList.options[typeSelectionList.selectedIndex].value;
            return selectedValue=="procedure";
        }
    </script>

    <script type="text/javascript">
        function setProcOrMedPO(){
            ${prescription.procOrMedicine.type}=${prescription.scratch.typeTherapy};
            ${prescription.procOrMedicine.name}=${prescription.scratch.typeTherapyName};
        }
    </script>
</head>
<body>
<h1>Page for prescription adding</h1>
<form:form modelAttribute="prescription" method="post" action="add_page" id="submitForm">
    <p><b>Form:</b></p>
    <p>Type</p>
<%--
    <form:label path="scratch.type.therapyType" for="typeSelectionList">Choose type of therapy:</form:label>
--%>
    <form:select path="scratch.typeTherapy" id="typeSelectionList" name="typeOfTherapy">
        <c:forEach items="<%=TherapyType.values()%>" var="therapyType">
            <form:option value="${therapyType}">
                ${therapyType.toString()}
            </form:option>
        </c:forEach>
    </form:select>

    <form:select path="scratch.type.therapyName" id="medicineSelectionList" name="nameOfMedicine" onchange="setProcOrMedPo()">
    <c:choose>
        <c:when test="document.getElementById(typeSelectionList).options[typeSelectionList.selectedValue].value == <%=TherapyType.MEDICINE.toString()%>">
                <c:forEach items="${medicines}" var="medicine">
                    <form:option value="${medicine}">${medicine.name}</form:option>
                </c:forEach>
        </c:when>

        <c:when test="isProcedure()">
                <c:forEach items="${procedures}" var="procedure">
                    <form:option value="${procedure}">${procedure.name}</form:option>
                </c:forEach>
        </c:when>

        <c:otherwise>
            <form:option value="">Please, select type of Therapy</form:option>
        </c:otherwise>
    </c:choose>
    </form:select>

    <p>Time Pattern</p>
    <form:label path="scratch.timePatternQuantity" for="quantityInTP">quantity in time pattern:</form:label>
    <form:input path="scratch.timePatternQuantity" type="text" id="quantityInTP" name="timePatternQuantity" placeholder="Enter time pattern quantity"/>
    at
    <form:select path="scratch.timePatternTimePeriod" id="timePeriodSelectionListInTP" name="timePatternTimePeriod">
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

    <br>
    <input id="submitButton" type="submit" value="Submit" onclick="added()"/>

    <script>
        function added(){
            alert("prescription added");
        }
    </script>
</form:form>
</body>
</html>
