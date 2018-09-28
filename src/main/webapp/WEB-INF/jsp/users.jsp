<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://project.voting.ru/functions" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="../../index.html">Home</a></h3>
<h2>Users</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>name</th>
        <th>email</th>
        <th>Статус активации</th>
    </tr>
    </thead>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.enabled}</td>

        </tr>
    </c:forEach>
</table>
</body>
</html>