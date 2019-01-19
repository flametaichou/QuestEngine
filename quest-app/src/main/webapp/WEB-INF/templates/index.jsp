<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true"%>

<html ng-app="QuestApp">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link rel="icon" href="favicon.ico" />

    <title>Quest Engine</title>

    <!--TODO: min.js -->
    <link href="<c:url value='/static/css/bootstrap/bootstrap.min.css' />" rel="stylesheet" media="screen"/>
    <link href="<c:url value='/static/css/font-awesome/font-awesome-4.7.0/css/font-awesome.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <script src="<c:url value='/static/js/angular-1.5.11/angular.js' />"></script>
    <script src="<c:url value='/static/js/angular-1.5.11/angular-route.js' />"></script>
    <script src="<c:url value='/static/js/angular-route-segment/route-segment.js' />"></script>
    <script src="<c:url value='/static/js/angular-route-segment/view-segment.js' />"></script>
    <script src="<c:url value='/static/js/ui-bootstrap/ui-bootstrap-tpls-3.0.4.js' />"></script>
    <script src="<c:url value='/static/js/app.js' />"></script>
    <script src="<c:url value='/static/js/controller/controller_accounts.js' />"></script>
    <script src="<c:url value='/static/js/controller/controller_quest_editor.js' />"></script>
    <script src="<c:url value='/static/js/controller/controller_dashboard.js' />"></script>

    <style>
        body {
            background-repeat: no-repeat;
            background: #f5f5f5 url(<c:url value='/static/images/background.png' />) center fixed;
            background-size: cover;
        }
    </style>
</head>

<body >
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="<c:url value='#' />">Quest Engine</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='#pages/main' />">
                        <spring:message code="menu.main"/>
                    </a>
                </li>
                <security:authorize access="hasAnyRole({'USER', 'CREATOR', 'ADMIN'})">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='#pages/dashboard' />">
                            <spring:message code="menu.dashboard"/>
                        </a>
                    </li>
                </security:authorize>
                <security:authorize access="hasRole('ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='#pages/admin/accounts' />">Пользователи</a>
                    </li>
                </security:authorize>
            </ul>
            <ul class="navbar-nav mt-2 mt-md-0">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li class="nav-item">
                        <span style="display: block; padding-bottom: .1rem; padding-top: .5rem; color: dimgray;">Вы вошли как: ${pageContext.request.userPrincipal.name}</span>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/logout" />">Выход</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
</header>

<div name="header" style="height: 56px;"></div>
<div style="height: 15px;"></div>
<div class="col-lg-12" app-view-segment="0"></div>

</body>
</html>