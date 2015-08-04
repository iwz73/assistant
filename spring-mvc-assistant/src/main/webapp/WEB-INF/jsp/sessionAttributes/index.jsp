<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session attributes</title>
</head>
<body>
	session scope parameter:${sessionScope.parameter}
	<hr>
	<a href="setSessionParameter?parameter=parameter">set
		session parameter</a>
	<br>
	<a href="clearSession">clear session</a>
</body>
</html>