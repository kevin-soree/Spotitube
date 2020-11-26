<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 30-3-2017
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Playlist by owner</title>
</head>
<body>
<h1>Playlists</h1>
<table>
    <thead>
    <th>Owner</th>
    <th>Name</th>
    </thead>
    <tbody>
    <c:forEach items="${playlists}" var="playlist">
        <tr>
            <td>
                <c:out value="${playlist.owner}"/>
            </td>
            <td>
                <c:out value="${playlist.name}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
