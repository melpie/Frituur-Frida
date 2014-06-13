<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<!doctype html>

<html lang="nl">
    <head>
        <title>Frituur Frida – Sausen met een ingredi&euml;ent</title>
        <link rel="stylesheet" href="${contextPath}/styles/default.css"/>
    </head>
    
    <body>
        <c:import url="/WEB-INF/JSP/menu.jsp" />
        <h1>Sausen ingrediënten</h1>
		<c:url value="/sausen/ingredienten" var="url"/>
		<form method="get" action="${url}">
		<label>Ingrediënt:
		<input name="ingredient" value="${param.ingredient}" autofocus/>
		<span class="fout">${fouten.ingredient}</span></label>
		<input type="submit" value="Zoeken"/>
		</form>
		<c:if test="${not empty sausen}">
			<ul>
			<c:forEach var="saus" items="${sausen}">
				<li>${saus.naam}</li>
			</c:forEach>
			</ul>
		</c:if>
        <c:if test="${empty fouten and sausen.size()==0}">
            <div class="fout">geen sausen gevonden</div>
        </c:if>
    </body>
</html>