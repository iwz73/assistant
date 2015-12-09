<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>facebook token</title>
<script>
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id)) {
			return;
		}
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));

	window.fbAsyncInit = function() {
		FB.init({
			appId : '1596227047295405',
			xfbml : true,
			version : 'v2.3'
		});

		FB.login(function(response) {
			statusChangeCallback(response);
		});
	};

	function checkLoginState() {
		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
	}

	function statusChangeCallback(response) {
		var info = document.getElementById('info');
		info.innerHTML = "";
		if (response.status === 'connected') {
			longLivedToken(info, response);
			appAccessToken(info);
			tokenInfo(info, response);
		} else if (response.status === 'not_authorized') {
			info.innerHTML = 'not authorized !!!';
		} else {
			info.innerHTML = 'not login !!!';
		}
	}

	function longLivedToken(info, response) {
		var accessToken = response.authResponse['accessToken'];
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				info.innerHTML = info.innerHTML + xhttp.responseText
						+ "<br><hr>";
			}
		};
		xhttp.open("GET", "longLivedToken?accessToken=" + accessToken, true);
		xhttp.send();
	}

	function appAccessToken(info) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				info.innerHTML = info.innerHTML + xhttp.responseText
						+ "<br><hr>";
			}
		};
		xhttp.open("GET", "appAccessToken", true);
		xhttp.send();
	}
	
	function tokenInfo(info, response) {
		var accessToken = response.authResponse['accessToken'];
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				info.innerHTML = info.innerHTML + xhttp.responseText
						+ "<br><hr>";
			}
		};
		xhttp.open("GET", "tokenInfo?accessToken=" + accessToken, true);
		xhttp.send();
	}
</script>
</head>
<body>
	<div class="fb-login-button" data-auto-logout-link="true"
		onlogin="checkLoginState();"></div>
	<hr>
	<div id="info"></div>
</body>

</html>