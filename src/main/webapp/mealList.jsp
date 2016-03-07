<%@ page import="ru.javawebinar.topjava.model.UserMealWithExceed" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: askandar
  Date: 05.03.16
  Time: 2:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal List users with Exceed</title>
</head>
<body>

<h2 align="center">Meal List user with Exceed</h2>
<div align="center" style="width: 40%; margin: auto ">


    <c:if test="${not empty users }">
        <table cellpadding="0" cellspacing="1" width="100%" border="1">
            <thead>
            <tr>
                <th>Date Time</th>
                <th>Desription</th>
                <th>Calories</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr style="border: 2px solid ${user.isExceed() ==true ? 'red':'green'}">
                    <td>${user.getDescription()}</td>
                    <td>${user.getCalories()}</td>
                    <td>${user.getDateTime().toLocalDate()}, ${user.getDateTime().toLocalTime()}</td>
                    <td><a  href="mealList?action=edit&userId=<c:out value="${user.getId()}"/>">Edit</a> | <a href="mealList?action=delete&userId=<c:out value="${user.getId()}"/>">Delete</a> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <a href="mealList?action=add">Add user Meal</a>
</div>

</body>
</html>
