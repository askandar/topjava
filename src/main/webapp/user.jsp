<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: askandar
  Date: 06.03.16
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="mealList">
    <table>
        <tr>
            <td>Date</td>
            <td><input type="text" name="date" value="<c:out value="${user.getDateTime().toLocalDate()}"/>"></td>
        </tr>
        <tr>
            <td>Time</td>
            <td><input type="text" name="time" value="<c:out value="${user.getDateTime().toLocalTime()}"/>"></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><input type="text" name="description" value="<c:out value="${user.getDescription()}"/>"></td>
        </tr>
        <tr>
            <td>Calories</td>
            <td><input type="text" name="calories" value="<c:out value="${user.getCalories()}"/>"></td>
        </tr>
    </table>
    <input type="hidden" name="userId" value="<c:out value="${user.getId()}"/>">

    <button type="submit">Submit</button>
    <button type="button" onclick="location.href='mealList'">Cancel</button>

</form>
</body>
</html>
