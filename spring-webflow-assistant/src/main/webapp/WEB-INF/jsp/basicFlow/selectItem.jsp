<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>select Item</title>
</head>
<body>
	<form:form modelAttribute="basicFlowVo" action="${flowExecutionUrl}">
		<form:select path="itemId" items="${itemMap}" />
		<input type="submit" name="_eventId_previous" value="previous">
		<input type="submit" name="_eventId_add" value="add">
		<input type="submit" name="_eventId_finish" value="finish">
		<input type="submit" name="_eventId_reset" value="reset">
		<input type="submit" name="_eventId_cancel" value="cancel">
	</form:form>
	selected item ids : <br>
	<c:forEach items="${basicFlowVo.selectedItemIds}" var="var">
		${var}<br>
	</c:forEach>
	<jsp:include page="include/info.jsp"></jsp:include>
</body>
</html>