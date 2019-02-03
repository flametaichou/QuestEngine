<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true"%>

<html ng-app="QuestViewerApp">
<head>
    <jsp:include page="import.jsp"/>
    <jsp:include page="import-viewer.jsp"/>
</head>

<body>
    <div app-view-segment="0"></div>
</body>
</html>