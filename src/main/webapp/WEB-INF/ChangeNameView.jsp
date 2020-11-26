<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 4-4-2017
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change playlist name</title>
</head>
<body>
<h1>Change playlist name</h1>
${playlist.ID}
<form action="/playlists/changename" method="POST">
    Name:<input type="text" name="name" value="${playlist.name}"/><br/><br/>
    <input type="hidden" name="playlistID" value="${playlist.ID}"/>
    <input type="submit" value="Change"/>
</form>
</body>
</html>
