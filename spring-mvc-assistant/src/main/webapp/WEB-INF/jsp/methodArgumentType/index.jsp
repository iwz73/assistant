<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>method argument type</title>
</head>
<body>
	parameter:${parameter}
	<hr>
	<h5>method argument type</h5>
	<a href="servletRequest?parameter=servletRequest">servletRequest</a>
	<br>
	<form action="multipartRequest" method="post"
		enctype="multipart/form-data">
		<input type="file" name="uploadedFile"> <input type="submit"
			value="multipartRequest submit">
	</form>
	<br>
	<form action="multipartHttpServletRequest" method="post"
		enctype="multipart/form-data">
		<input type="file" name="uploadedFile"> <input type="submit"
			value="multipartHttpServletRequest submit">
	</form>
	<a href="httpSession">httpSession</a>
	<br>
	<a href="webRequest?parameter=webRequest">webRequest</a>
	<br>
	<a href="webRequest?parameter=nativeWebRequest">nativeWebRequest</a>
	<br>
	<a href="locale">locale</a>
	<br>
	<a href="principal">principal</a>
	<br>
	<a
		href="requestParam?integerValue=1&floatValue=2.2&stringValue=stringValue">requestParam</a>
	<br>
	<form action="redirectAttributes" method="post">
		<input type="text" name="parameter" value="parameter"> <input
			type="submit" value="redirectAttributes submit">
	</form>
	<br>
	<form action="criteria" method="get">
		<input type="number" name="integerValue" value="1"> <input
			type="number" name="floatValue" value="2.2"> <input
			type="text" name="stringValue" value="stringValue"> <input
			type="date" name="dateValue" value="2015/06/01"> <input
			type="date" name="localDate" value="2015-06-01"> <input
			type="date" name="localTime" value="18:30:45"> <input
			type="date" name="localDateTime" value="2015-06-01T18:30:45">
		<input type="submit" value="criteria submit">
	</form>
</body>
</html>