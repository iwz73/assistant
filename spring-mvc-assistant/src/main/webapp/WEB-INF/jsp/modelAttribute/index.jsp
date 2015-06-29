<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>model attribute index</title>
</head>
<body>
<form:form modelAttribute="criteria" method="get" action="index"> 
	<form:label path="input">input</form:label>
	<form:input path="input" />
	<br>
	<form:label path="password">password</form:label>
	<form:password path="password"  />
	<br>
	<form:label path="textarea">textarea</form:label>
	<form:textarea path="textarea" rows="5" cols="30" />
	<br>
	<form:checkbox path="singleCheckbox" /> single checkbox
	<br>
	<form:checkbox path="multiCheckboxs" value="multiCheckboxs_1" /> multi checkboxs 1
	<form:checkbox path="multiCheckboxs" value="multiCheckboxs_2" checked="checked" /> multi checkboxs 2
	<form:checkbox path="multiCheckboxs" value="multiCheckboxs_3" /> multi checkboxs 3
	<br>
	<form:checkboxes path="checkboxs" items="${checkboxs}" />
	<br>
	<form:radiobutton path="radiobutton" value="radiobutton_1" />radiobutton 1
	<form:radiobutton path="radiobutton" value="radiobutton_2" checked="checked" />radiobutton 2
	<br>
	<form:radiobuttons path="radiobuttons" items="${radiobuttons}" />
	<br>
	<form:label path="select">select</form:label>
	<form:select path="select" items="${select}" multiple="false" />
	<br>
	<form:label path="options">options</form:label>
	<form:select path="options">
		<form:option value="" label="--"/>
		<form:options items="${options}" />
	</form:select>
	<br>
	<form:label path="hidden">hidden</form:label>
	<form:hidden path="hidden" value="hidden" />
	<br>
	<input type="submit" value="submit">
</form:form>
<hr>
<c:forEach items="${list}" var="var">
	${var}<br>
</c:forEach>
</body>
</html>