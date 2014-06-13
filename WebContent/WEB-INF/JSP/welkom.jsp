<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.Calendar"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Frituur Frida</title>
		<c:url value="/styles/default.css" var="URLDefaultCSS"/>
        <link rel="stylesheet" href="${URLDefaultCSS}"/>
	</head>
<body>
	<c:import url="/WEB-INF/JSP/menu.jsp"/>
	<h1>${openGesloten}</h1>
	<c:choose>
		<c:when test="${openGesloten == 'Vandaag zijn we open.'}">
			<img src="${pageContext.servletContext.contextPath}/images/open.png" alt="open"/>
		</c:when>
		<c:otherwise>
			<img src="${pageContext.servletContext.contextPath}/images/gesloten.png" alt="gesloten"/>
		</c:otherwise>
	</c:choose>
	<h2>Adres</h2>
    ${adres.straat} ${adres.huisNr} <br>
    ${adres.gemeente.postCode} ${adres.gemeente.naam}
</body>
</html>