<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ica.oose.domain.Playlist" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 23-3-2017
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Playlist</title>
</head>
<body>
<h1>All playlists</h1>
<table>
    <thead>
    <th>ID</th>
    <th>Owner</th>
    <th>Name</th>
    </thead>
    <tbody>
    <c:forEach items="${allPlaylists}" var="playlist">
        <tr>
            <td>
                <a href="/playlist?id=<c:out value="${playlist.ID}"/>"> <c:out value="${playlist.ID}"/></a>
            </td>
            <td>
                <c:out value="${playlist.owner}"/>
            </td>
            <td>
                <c:out value="${playlist.name}"/>
            </td>
            <td>
                <a href="/playlists/changename?id=<c:out value="${playlist.ID}"/>"> change name</a>
            </td>
            <td>
                <form action="playlists" method="POST">
                    <input type="hidden" name="playlistID" value="${playlist.ID}">
                    <input type="submit" value="Delete playlist"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="/playlists/createplaylist">Create</a> / <a href="/search">Search</a></p>
</body>
</html>
