<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>method argument type</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>integerValue</th>
				<th>floatValue</th>
				<th>stringValue</th>
				<th>dateValue</th>
			</tr>
		</thead>
		<tbody>		
			<c:forEach items="${dataList}" var="v">
				<tr>
					<td>${v.integerValue}</td>
					<td>${v.floatValue}</td>
					<td>${v.stringValue}</td>
					<td><fmt:formatDate pattern="yyyy/MM/dd" value="${v.dateValue}"/></td>
				</tr>
			</c:forEach>			
		</tbody>
	</table>

</body>
</html>