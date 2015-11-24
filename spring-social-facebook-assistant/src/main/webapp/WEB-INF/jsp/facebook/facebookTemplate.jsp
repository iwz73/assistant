<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>facebook facebook template</title>
<script>
	var accessToken;

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
		if (response.status === 'connected') {
			accessToken = response.authResponse['accessToken'];
			info.innerHTML = 'connected.';
		} else if (response.status === 'not_authorized') {
			info.innerHTML = 'not authorized !!!';
		} else {
			info.innerHTML = 'not login !!!';
		}
	}

	function facebookTemplate(loc) {
		var info = document.getElementById('info');
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				info.innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("GET", generateUrl(loc), true);
		xhttp.send();
	}

	function generateUrl(loc) {
		var url = loc + "?accessToken=" + accessToken;
		if (loc === 'facebookTemplateOtherUser') {
			url = url + "&otherUserId=10203958109832579";
		}
		return url;
	}
</script>
</head>
<body>
	<div class="fb-login-button" data-auto-logout-link="true"
		onlogin="checkLoginState();"></div>
	<br>
	<input type="button" value="facebook template user"
		onclick="facebookTemplate('facebookTemplateUser');">
	<input type="button" value="facebook template user"
		onclick="facebookTemplate('facebookTemplateOtherUser');">
	<hr>
	<div id="info"></div>
</body>

</html>