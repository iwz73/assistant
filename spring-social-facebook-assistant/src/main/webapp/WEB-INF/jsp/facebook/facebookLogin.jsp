<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>facebook facebook login</title>
<script>
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
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

		FB.getLoginStatus(function(response) {
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
		if (response.status === 'connected') {
			showInfo(response);
		} else if (response.status === 'not_authorized') {
			info.innerHTML = 'not authorized !!!';
		} else {
			info.innerHTML = 'not login !!!';
		}
	}

	function showInfo(response) {
		var info = document.getElementById('info');
		info.innerHTML = "";
		var auth = response.authResponse;
		for (k in auth) {
			info.innerHTML = info.innerHTML + k + " : " + auth[k]
					+ "<br>";
		}
		info.innerHTML = info.innerHTML + "<hr>";
		FB.api('/me', function(response) {
			for (k in response) {
				info.innerHTML = info.innerHTML + k + " : " + response[k]
						+ "<br>";
			}
		});
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