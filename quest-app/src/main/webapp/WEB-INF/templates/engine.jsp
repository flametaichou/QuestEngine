<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true"%>

<html ng-app="QuestEngineApp">
<head>
    <jsp:include page="import.jsp"/>
    <jsp:include page="import-engine.jsp"/>
</head>

<body>
<div class="engine-menu">
    <div style="padding: 20px;">
        <img src="<c:url value='/static/images/logo.png' />" style="width: 80%;"/>
        <div style="font-size: 10pt; margin-top: 5px;">
            <span style="color: dimgray;">Вы вошли как: ${pageContext.request.userPrincipal.name}</span>
            <a class="a-white-button" style="width: 60px; margin-left: 5px; display: inline-block;" href="<c:url value="/logout" />">Выход</a>
        </div>
    </div>
    <a ng-class="('main' | routeSegmentContains) ? 'engine-menu-element active' : 'engine-menu-element'"
       href="<c:url value='#pages/main' />">
        <spring:message code="menu.main"/>
    </a>
    <security:authorize access="hasAnyRole({'USER', 'CREATOR', 'ADMIN'})">
        <a ng-class="('dashboard' | routeSegmentContains) ? 'engine-menu-element active' : 'engine-menu-element'"
           href="<c:url value='#pages/dashboard' />">
            <spring:message code="menu.dashboard"/>
        </a>
    </security:authorize>
    <security:authorize access="hasRole('ADMIN')">
        <a ng-class="('accounts' | routeSegmentContains) ? 'engine-menu-element active' : 'engine-menu-element'"
           href="<c:url value='#pages/admin/accounts' />">
            Пользователи
        </a>
    </security:authorize>
</div>
<div class="engine-content">
    <div app-view-segment="0" style="padding: 15px;"></div>
</div>
</body>

<!--
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
            </div>
        </nav>
    </header>

    <div name="header" style="height: 56px;"></div>
    <div class="indent-xs"></div>

    </body>
    -->
</html>