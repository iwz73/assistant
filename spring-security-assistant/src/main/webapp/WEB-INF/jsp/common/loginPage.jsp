<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<body>
	<form action="<c:url value='/loginProcessingUrl' />" method='POST'>
		username: <input type='text' name='username' /><br>
		password : <input type='password' name='password' /><br> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
		<input type="submit" />
	</form>
</body>
</html>
