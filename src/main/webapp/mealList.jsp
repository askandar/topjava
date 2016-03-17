<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h3>Meal list</h3>
    <form id="filter" method="get">
        <%
            String fromDate = (String)request.getAttribute("fromDate");
            String toDate = (String)request.getAttribute("toDate");
            String fromTime = (String)request.getAttribute("fromTime");
            String toTime = (String)request.getAttribute("toTime");
           if (fromDate == "" | fromDate == null ) {
               fromDate = "2015-01-01";
               toDate = "2016-01-01";
               fromTime = "00:00";
               toTime = "23:59";
           }


        %>

        <label>From Date:</label><input name="fromDate" type="date" value="<%=fromDate%>"/> <label>To Date:</label><input name="toDate" type="date" value="<%=toDate%>"/>
        <br />
        <br />
        <label>From Time:</label><input name="fromTime" type="time" value="<%=fromTime%>" /> <label>To Time:</label><input name="toTime" type="time" value="<%=toTime%>" />
        <br />
        <br />
        <button type="submit" >Filter</button>
    </form>

    <a href="meals?action=create">Add Meal</a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        ${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>