<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<hr>
categoryId : ${basicFlowVo.categoryId}<br>
itemId : ${basicFlowVo.itemId}<br>
selectedItemIds:<br>
<c:forEach items="${basicFlowVo.selectedItemIds}" var="var">
	${var}<br>
</c:forEach>
<br>