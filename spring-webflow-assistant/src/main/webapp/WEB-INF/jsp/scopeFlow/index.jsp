<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
	<spring:url value="/scopeFlow" var="scopeFlow" />
	<form:form action="${scopeFlow}" method="post">
		<input type="submit" value="start flow">
	</form:form>
	<jsp:include page="include/info.jsp"></jsp:include>
</body>
</html>