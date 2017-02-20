<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>flow_0</title>
</head>
<body>
	<form:form modelAttribute="basicVo" action="${flowExecutionUrl}">
		<input type="text" name="name">
		<input type="text" name="age">
		<input type="button" name="_eventId_next" value="next">
		<input type="button" name="_eventId_cancel" value="cancel">
	</form:form>
</body>
</html>