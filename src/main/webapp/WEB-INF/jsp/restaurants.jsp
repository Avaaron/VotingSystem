<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://project.voting.ru/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Рестораны</title>
</head>
<body>
<h2>Рестораны</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Название</th>

    </tr>
    </thead>
    <c:forEach items="${restaurants}" var="restaurant">
        <tr>
            <td>${restaurant.name}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
