<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h3>User list</h3>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
            <th>id</th>
            <th>User name</th>
            <th>Email</th>
            <th>Role</th>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <jsp:useBean id="user" scope="page" type="ru.javawebinar.topjava.model.User" />
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.roles}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

<%--
System.out.println(adminUserController.create(new User(LoggedUser.id(), "userName", "email@mail.ru", "password", Role.ROLE_ADMIN)));--%>
