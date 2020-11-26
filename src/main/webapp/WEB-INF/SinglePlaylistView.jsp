<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 3-4-2017
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Playlist: ${playlist.name}</title>
</head>
<body>
<h1>Playlist: ${playlist.name}</h1>
<table>
    <thead>
    <th>ID</th>
    <th>performer</th>
    <th>title</th>
    <th>duration</th>
    <th>album</th>
    <th>playcount</th>
    <th>publicationdate</th>
    <th>description</th>
    <th>availability</th>
    </thead>
    <tbody>
    <c:forEach items="${playlist.tracks}" var="track">
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
                <c:out value="${track.duration}"/>
            </td>
            <td>
                <c:if test="${track.getClass().name == 'ica.oose.domain.Song'}">
                <c:out value="${track.album}"/>
                </c:if>
            </td>

        <%--${track.getClass().name}--%>

                <td>
                    <c:if test="${track.getClass().name == 'ica.oose.domain.Video'}}">
                    <c:out value="${track.playcount}"/>
                    </c:if>
                </td>
                <td>
                    <c:if test="${track.getClass().name == 'ica.oose.domain.Video'}}">
                    <c:out value="${track.publicationdate}"/>
                    </c:if>
                </td>
                <td>
                    <c:if test="${track.getClass().name == 'ica.oose.domain.Video'}}">
                    <c:out value="${track.description}"/>
                    </c:if>
                </td>

            <td>
                <c:out value="${track.availability.offlineAvailability}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
