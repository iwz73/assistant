<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>client-side authentication</title>
</head>
<body>
	access_token :
	<div id="access_token"></div>
	<button onclick="javascritp:getClientSideAuthentication();">get
		client-side access_token</button>
</body>
<script>
	function getHashValue() {
		var match = location.hash.match(new RegExp("access_token=(.*)"));
		return match ? match[1] : null;
	}

	function getClientSideAuthentication() {
		var client_id = "d9f9a50903ea4e1092469b4804237a5a";
		var redirect_uri = "http://localhost:8080/instagram-assistant/instagram/clientSideAuthentication";
		var response_type = "token";
		window.open("https://instagram.com/oauth/authorize/?client_id="
				+ client_id + "&redirect_uri=" + redirect_uri
				+ "&response_type=" + response_type, "_self");
	}

	document.getElementById("access_token").innerHTML = getHashValue();
</script>
</html>