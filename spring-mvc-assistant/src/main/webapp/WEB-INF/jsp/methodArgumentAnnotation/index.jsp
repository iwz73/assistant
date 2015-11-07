<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>method argument annotation</title>
<script
	src='<c:url value="/javascript/jquery-2.1.4/jquery-2.1.4.min.js"></c:url>'></script>
<script type="text/javascript">
	$(function() {
		$("#requestBody").on("click", function() {
			$.ajax({
				url : '<c:url value="/methodArgumentAnnotation/requestBody.json"></c:url>',
				method : 'POST',
				contentType : 'application/json; charset=UTF-8',
				data: JSON.stringify({ 
					"integerValue" : 1,
					"floatValue" : 2.2,
					"stringValue" : "this is a string value.",
					"dateValue" : "2015-01-01T18:30:45",
					"localDate" : "2015-01-02",
					"localTime" : "18:30:45",
					"localDateTime" : "2015-01-02T18:30:45"
					
				})
			}).done(function(data, textStatus,jqXHR) {
				$("#requestBodyResult").html(JSON.stringify(data));
			});
		});
	});
</script>
</head>
<body>
	<span id="requestBodyResult">parameter:${parameter}</span>
	<hr>
	<a
		href='<c:url value="/methodArgumentAnnotation/pathVariable/3"></c:url>'>path
		variable</a>
	<br>
	<a href="javascript:void(0);" id="requestBody">request body</a> 
	<br>
</body>
</html>