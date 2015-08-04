<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tag index</title>
</head>
<body>
	call static method from jsp : ${parameter} * ${parameter} =
	<s:eval
		expression="T(idv.hsiehpinghan.springmvcassistant.utility.StaticMethodUtility).getSquare(${parameter})" />
	<hr>
</body>
</html>