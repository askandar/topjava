<%@ page import="ru.javawebinar.topjava.model.UserMealWithExceed" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
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
        <table cellpadding="0" cellspacing="1" width="100%" border="1" align="center">
            <thead>
            <tr>
                <th>Date Time</th>
                <th>Desription</th>
                <th>Calories</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody align="center">
            <c:forEach items="${users}" var="user">
                <jsp:useBean id="user" scope="page" type="ru.javawebinar.topjava.model.UserMealWithExceed"/>
                <tr style="border: 2px solid ${user.exceed ? 'red':'green'}">
                    <td><%=TimeUtil.toString(user.getDateTime())%></td>
                    <td>${user.description}</td>
                    <td>${user.calories}</td>
                    <td><a  href="mealList?action=edit&userId=<c:out value="${user.id}"/>">Edit</a> | <a href="mealList?action=delete&userId=<c:out value="${user.id}"/>">Delete</a> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <a href="mealList?action=add">Add user Meal</a>
</div>

</body>
</html>
