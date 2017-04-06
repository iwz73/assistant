<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view state 0</title>
</head>
<body>
	<form:form action="${flowExecutionUrl}">
<!-- 		<input type="submit" name="_eventId_next" value="next"> -->
<!-- 		<input type="submit" name="_eventId_cancel" value="cancel"> -->
		<input type="submit" name="_eventId_initScope" value="initScope">
	</form:form>
	<jsp:include page="include/info.jsp"></jsp:include>
</body>
</html>