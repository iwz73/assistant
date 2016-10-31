<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>internationalization index</title>
</head>
<body>
	<a
		href="<c:url value='/internationalization/index?language=zh_TW'></c:url>">zh_TW</a>
	|
	<a
		href="<c:url value='/internationalization/index?language=en'></c:url>">en</a>
	<br>
	current internationalize : ${currentInternationalize}
	<br>
	internationalize info :
	<spring:message code="internationalizeInfo" />
</body>
</html>