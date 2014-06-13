<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<html lang="nl">
	<head>
		<title>Meisjes jongens</title>
		<link rel="stylesheet" href="${contextPath}/styles/default.css"/>
		<c:if test="${not empty cookie.meisjesjongens.value}">
			<link rel="stylesheet" href="${contextPath}/styles/${cookie.meisjesjongens.value}.css"/>
		</c:if>
	</head>
	<body>
		<c:import url="/WEB-INF/JSP/menu.jsp"/>
		<c:url value="/meisjesjongens" var="action"/>
		<h1>Meisjes jongens</h1>
		<form method="post" action="${action}" id="geslachtform">
			<input type="submit" name="meisjes" value="meisjes" id="meisjes"/>
			<input type="submit" name="jongens" value="jongens" id="jongens">
		</form>
	</body>
</html>