<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 26.01.2021
  Time: 3:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">

        function changeFunc() {
            var typeSelectionList = document.getElementById("typeSelectionList");
            var selectedValue = typeSelectionList.options[typeSelectionList.selectedIndex].value;
        }

    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script>
            $('.table-filters input').on('input', function () {
            filterTable($(this).parents('table'));
        });

            function filterTable($table) {
            var $filters = $table.find('.table-filters td');
            var $rows = $table.find('.table-data');
            $rows.each(function (rowIndex) {
            var valid = true;
            $(this).find('td').each(function (colIndex) {
            if ($filters.eq(colIndex).find('input').val()) {
            if ($(this).html().toLowerCase().indexOf(
            $filters.eq(colIndex).find('input').val().toLowerCase()) === -1) {
            valid = valid && false;
        }
        }
        });
            if (valid === true) {
            $(this).css('display', '');
        } else {
            $(this).css('display', 'none');
        }
        });
        }
    </script>
</head>
<body>
<h1>Events page</h1>

<table id="eventsTable">
    <thead>
    <tr>
        <th>Patient's surname</th>
        <th>Patient's name</th>
        <th>Date and time</th>
        <th>Status</th>
        <th>Event Type</th>
    </tr>
    </thead>
    <tbody>
    <tr class='table-filters'>
        <td><input type="text"/></td>
        <td><input type="text"/></td>
        <td>
            <label for="timeFilter">timeFilter:</label>
            <select id="timeFilter" name="timeFilterType" onchange="changeFunc();">
                <option value="forHour">for hour</option>
                <option value="forDay">for day</option>
                <option selected value="allTime">all time</option>
            </select>
        </td>
    </tr>
    <c:choose>
        <c:when test="document.getElementById.timeFilter.options[typeSelectionList.selectedIndex].value==for hour">
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>${event.patient.lastName}</td>
                    <td>${event.patient.firstName}</td>
                    <td>${event.dateAndTime}</td>
                    <td>${event.status}</td>
                    <td>${event.eventType.name}</td>
                    <td><button id="cancel2" onclick="cancelEvent.js">change status to "cancel"</button></td>
                    <td><button id="done2" onclick="doneEvent.js">change status to "done"</button> </td>
                </tr>
            </c:forEach>
        </c:when>

        <c:when test="document.getElementById.timeFilter.options[typeSelectionList.selectedIndex].value==for day">
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>${event.patient.lastName}</td>
                    <td>${event.patient.firstName}</td>
                    <td>${event.dateAndTime}</td>
                    <td>${event.status}</td>
                    <td>${event.eventType.name}</td>
                    <td><button id="cancel3" onclick="cancelEvent.js">change status to "cancel"</button></td>
                    <td><button id="done3" onclick="doneEvent.js">change status to "done"</button> </td>
                </tr>
            </c:forEach>
        </c:when>

        <c:otherwise>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>${event.patient.lastName}</td>
                    <td>${event.patient.firstName}</td>
                    <td>${event.dateAndTime}</td>
                    <td>${event.status}</td>
                    <td>${event.eventType.name}</td>
                    <td><button id="cancel1" onclick="cancelEvent.js">change status to "cancel"</button></td>
                    <td><button id="done1" onclick="doneEvent.js">change status to "done"</button> </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

</body>
</html>
