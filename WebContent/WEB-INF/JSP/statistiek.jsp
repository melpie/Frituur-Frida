<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<!doctype html>
<html lang="nl">
    <head>
        <title>Pizza Luigi - statistiek</title>
        <link rel="stylesheet" href="${contextPath}/styles/default.css"/>
    </head>
    
    <body>
        <c:import url="/WEB-INF/JSP/menu.jsp"/>
        <h1>Statistiek</h1>
        <c:if test="${not empty statistiek}">
            <table>
                <caption>Request statistiek</caption>
                <thead>
                    <tr>
                        <th>URL</th>
                        <th>aantal requests</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${statistiek}">
                        <tr>
                            <td>${entry.key}</td>
                            <td style="text-align:right;">${entry.value}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>