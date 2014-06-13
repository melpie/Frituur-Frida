<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<!doctype html>

<html lang="nl">
	
	<head>
		<title>Frituur Frida - sausen raden</title>
	</head>
	
	<body>
		<c:import url="/WEB-INF/JSP/menu.jsp"/>
		<h1>Sausen raden</h1>
		<c:choose>
			<c:when test="${spel.verloren}">
				U bent verloren, de saus was ${spel.saus}.
			</c:when>
			<c:when test="${spel.gewonnen}">
				U bent gewonnen, de saus was ${spel.saus}.
			</c:when>
			<c:otherwise>
				Te raden saus: ${spel.sausMetPuntjes}
				<c:url value="/sausen/raden" var="radenURL" />
				<form method="post" action="${radenURL}" id="radenform" >
					<label>letter:
					<input name="letter" size="1" maxlength="1" autofocus /></label>
					<input type="submit" value="Raden" id="radenknop"/>
				</form>
				<script>
					document.getElementById('radenform').onsubmit = function() {
						document.getElementById('radenknop').disabled = true;
					};
				</script>
			</c:otherwise>
		</c:choose>
		<c:url value="/sausen/raden" var="nieuwSpelURL">
			<c:param name="nieuwSpel" value="true"/>
		</c:url>
		<form method="post" action="${nieuwSpelURL}" id="nieuwspelform" >
			<input type="submit" value="Nieuw spel" id="nieuwspelknop"/>
		</form>
		<img src="${contextPath}/images/${spel.verkeerdeBeurten}.png"
		alt="${spel.verkeerdeBeurten} verkeerde beurten" />
		<script>
		document.getElementById('nieuwspelform').onsubmit = function() {
		document.getElementById('nieuwspelknop').disabled=true;
		};
		</script>
	</body>
	
</html>