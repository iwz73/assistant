<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>validation index</title>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
<form:form modelAttribute="criteria" method="get" action="validation"> 
	<form:errors path="*" cssClass="errorblock" element="div" />
	<form:label path="stringValue">not empty</form:label>
	<form:input path="stringValue" />
	<form:errors path="stringValue" cssClass="error" />
	<br>
	<form:label path="integerValue">input 1 ~ 100</form:label>
	<form:input path="integerValue" />
	<form:errors path="integerValue" cssClass="error" />
	<br>
	<input type="submit" value="submit">
</form:form>
</body>
</html>