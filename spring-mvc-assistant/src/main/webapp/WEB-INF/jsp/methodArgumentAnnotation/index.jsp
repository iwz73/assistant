<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>method argument annotation</title>
</head>
<body>
	parameter:${parameter}
	<hr>
	<a
		href='<c:url value="/methodArgumentAnnotation/pathVariable/3"></c:url>'>path
		variable</a>
</body>
</html>