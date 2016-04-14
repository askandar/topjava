<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%--<head>
    <title>Meal list</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>--%>

<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h2><a href="${pageContext.request.contextPath}/">Home</a></h2>
    <h3>Meal list</h3>
    <form method="post" action="meals/filter">
        <dl>
            <dt>From Date:</dt>
            <dd><input type="date" name="startDate" value="${startDate}"></dd>
        </dl>
        <dl>
            <dt>To Date:</dt>
            <dd><input type="date" name="endDate" value="${endDate}"></dd>
        </dl>
        <dl>
            <dt>From Time:</dt>
            <dd><input type="time" name="startTime" value="${startTime}"></dd>
        </dl>
        <dl>
            <dt>To Time:</dt>
            <dd><input type="time" name="endTime" value="${endTime}"></dd>
        </dl>
        <button type="submit">Filter</button>
    </form>
    <hr>
    <a href="meals/create">Add Meal</a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><fmt:message key="app.date"/> </th>
            <th><fmt:message key="app.description"/> </th>
            <th><fmt:message key="app.calories"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.UserMealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%=TimeUtil.toString(meal.getDateTime())%>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals/update?id=${meal.id}"><fmt:message key="app.update"/> </a></td>
                <td><a href="meals/delete?id=${meal.id}"><fmt:message key="app.delete"/> </a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
