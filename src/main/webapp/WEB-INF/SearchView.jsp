<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 3-4-2017
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Seach tracks</h1>
<form action="search" method="GET">
    Name:<input type="text" name="title"/><br/><br/>
    <input type="submit" value="Search"/>
</form>
<table>
    <thead>
    <th>ID</th>
    <th>performer</th>
    <th>title</th>
    </thead>
    <tbody>
    <c:forEach items="${tracks}" var="track">
        <tr>
            <td>
                <c:out value="${track.ID}"/>
            </td>
            <td>
                <c:out value="${track.performer}"/>
            </td>
            <td>
                <c:out value="${track.title}"/>
            </td>
            <td>
                <form action="search" method="POST">
                    <select name="playlist">
                <c:forEach items="${playlists}" var="playlist">
                    <option value="${playlist.ID}">${playlist.name}</option>
                </c:forEach>
                    </select>
                    <input type="hidden" name="trackID" value="${track.ID}">
                <input type="submit" value="Add to playlist"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
