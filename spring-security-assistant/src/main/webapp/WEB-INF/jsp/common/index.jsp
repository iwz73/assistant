<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<body>
	this is a common page. (${pageContext.request.userPrincipal.name})<br>
	<jsp:include page="../include/logoutForm.jsp"></jsp:include>
</body>
</html>
