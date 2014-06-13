<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Frituur Frida - Sausen</title>
        <link rel="stylesheet" href="${contextPath}/styles/default.css"/>
	</head>
	
	<body>
		<c:import url="/WEB-INF/JSP/menu.jsp"/>
		<h1>Sausen</h1>
		
			<c:url value="/sausen" var="url" />
       		<form method="post" action="${url}">
		
		        <ul class="zebra">
		            <c:forEach var="entry" items="${sausen}" varStatus="status">
		                <li class="${status.count % 2 == 0 ? 'even':'oneven'}">
		                    <label><input type="checkbox" name="nummer" value="${entry.nummer}"></label>
		                    ${entry.naam} : 
		                    <c:forEach var="ingredient" items="${entry.ingredienten}" varStatus="stat">
		                    	${ingredient}<c:if test="${!stat.last}">, </c:if>
		                    </c:forEach>
		                    <c:url value="/sausen/detail" var="detailURL">
		                        <c:param name="nummer" value="${entry.nummer}"/>
		                        <c:param name="naam" value="${entry.naam}"/>
		                    </c:url>
		                    <a href="<c:out value='${detailURL}'/>">Detail</a>
		                    <br>
		                    <c:url value="/images/${entry.naam}.jpg" var="URLImage"/>
		                    <img src="${URLImage}" alt="${entry.naam}"/>
		                </li>
		            </c:forEach>
		        </ul>
		   		
		   		<br>
		        <input type="submit" value="Aangevinkte sausen verwijderen"/>
		        
		    </form>
	        
	</body>
</html>