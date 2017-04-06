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
	<form:form modelAttribute="scopeFlowVo" action="${flowExecutionUrl}">
		<form:label path="requestScopeValue">requestScopeValue:</form:label><form:input path="requestScopeValue"/><br>
		<form:label path="flashScopeValue">flashScopeValue:</form:label><form:input path="flashScopeValue"/><br>
		<form:label path="viewScopeValue">viewScopeValue:</form:label><form:input path="viewScopeValue"/><br>
		<form:label path="conversationScopeValue">conversationScopeValue:</form:label><form:input path="conversationScopeValue"/><br>
		<form:label path="flowScopeValue">flowScopeValue:</form:label><form:input path="flowScopeValue"/><br>
		<input type="submit" name="_eventId_self" value="self">
		<input type="submit" name="_eventId_next" value="next">
		<input type="submit" name="_eventId_cancel" value="cancel">
	</form:form>
	<jsp:include page="include/info.jsp"></jsp:include>
</body>
</html>