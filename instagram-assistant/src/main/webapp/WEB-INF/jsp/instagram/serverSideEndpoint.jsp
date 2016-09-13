<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>server-side endpoint</title>
</head>
<body>
	<button onclick="javascritp:usersSelf('/users/self');">/users/self</button>
	<br>
</body>
<script>
	function usersSelf(endpoint) {
		var client_id = "${clientId}";
		var redirect_uri = "${serverSideEndpointRedirectUri}?endpoint="
				+ endpoint;
		var response_type = "code";
		window
				.open(
						"https://api.instagram.com/oauth/authorize/?client_id="
								+ client_id
								+ "&redirect_uri="
								+ redirect_uri
								+ "&response_type="
								+ response_type
								+ "&scope=basic+public_content+follower_list+comments+relationships+likes",
						"_self");
	}
</script>
</html>