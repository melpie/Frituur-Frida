<%@page contentType="text/html" pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header>
    <img src="${pageContext.servletContext.contextPath}/images/fridalogo.png" alt="logo"/>
    <nav>
        <ul class="menu">
            <c:url value="/" var="rootURL"/>
            <li><a href="${rootURL}">Welkom</a></li>
            <c:url value="/sausen" var="sausenURL"/>
            <li><a href="${sausenURL}">Sausen</a></li>
            <c:url value="/sausen/ingredienten" var="ingredientenURL"/>
            <li><a href="${ingredientenURL}">Ingredi&euml;nten</a></li>
            <c:url value="/meisjesjongens" var="mjURL"/>
            <li><a href="${mjURL}">Meisje of Jongen</a></li>
            <c:url value="/sausen/raden" var="raaddesausURL"/>
            <li><a href="${raaddesausURL}">Raad De Saus</a></li>    
            <c:url value="/statistieken" var="statURL"/>
            <li><a href="${statURL}">Statistieken</a></li>         
        </ul>
    </nav>
</header>