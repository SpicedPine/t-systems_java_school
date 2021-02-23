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
    <title>Events</title>
</head>
<body>
<h1>Events page</h1>
<p>
    <a href="<c:url value='/'/>">Back to personal cabinet</a>
</p>

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
            timeFilter:<br>
            <a href="<c:url value='/event/for_hour'/>">for hour</a><br>
            <a href="<c:url value='/event/for_day'/>">for day</a><br>
            <a href="<c:url value='/event/'/>">all time</a>
        </td>
    </tr>
    <c:forEach items="${eventsForHour}" var="event">
        <tr class="table-data">
            <td>${event.patient.lastName}</td>
            <td>${event.patient.firstName}</td>
            <td>${event.dateAndTime}</td>
            <td>${event.status}</td>
            <td>${event.eventType.name}</td>
            <td><a href="<c:url value="/event/${event.id}/changeToDone"/>">change status to "done"</a></td>
            <td><a href="<c:url value="/event/${event.id}/changeToCancelled"/>">change status to "canceled"</a></td>
        </tr>
    </c:forEach>
    </tbody>

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
</table>

</body>
</html>
