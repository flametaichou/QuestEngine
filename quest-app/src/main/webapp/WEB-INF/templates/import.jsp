<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true"%>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="icon" href="favicon.ico" />

<title>Quest Engine</title>

<!--TODO: min.js -->
<link href="<c:url value='/static/css/bootstrap/bootstrap.min.css' />" rel="stylesheet" media="screen"/>
<link href="<c:url value='/static/css/font-awesome/font-awesome-4.7.0/css/font-awesome.css' />" rel="stylesheet"/>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
<script src="<c:url value='/static/js/angular-1.5.11/angular.js' />"></script>
<script src="<c:url value='/static/js/angular-1.5.11/angular-route.js' />"></script>
<script src="<c:url value='/static/js/ui-bootstrap/ui-bootstrap-tpls-3.0.4.js' />"></script>
<script src="<c:url value='/static/js/angular-route-segment/route-segment.js' />"></script>
<script src="<c:url value='/static/js/angular-route-segment/view-segment.js' />"></script>

<script src="<c:url value='/static/js/components.js' />"></script>

<style>
    body {
        background-repeat: no-repeat;
        background: #f5f5f5 url(<c:url value='/static/images/background.png' />) center fixed;
        background-size: cover;
    }
</style>