<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>facebook facebook auto login</title>
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
			appId : '1394401980571844',
			xfbml : true,
			version : 'v2.8'
		});

		FB.login(function(response) {
			statusChangeCallback(response);
		});
	};

	function statusChangeCallback(response) {
		var info = document.getElementById('info');
		if (response.status === 'connected') {
			showInfo(info, response);
		} else if (response.status === 'not_authorized') {
			info.innerHTML = 'not authorized !!!';
		} else {
			info.innerHTML = 'not login !!!';
		}
	}

	function showInfo(info, response) {
		info.innerHTML = "";
		var auth = response.authResponse;
		for (k in auth) {
			info.innerHTML = info.innerHTML + k + " : " + auth[k] + "<br>";
		}
		info.innerHTML = info.innerHTML + "<hr>";
		showPersonalInfo(auth['accessToken']);
	}

	function showPersonalInfo(accessToken) {
		var info = document.getElementById('info');
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				info.innerHTML = info.innerHTML + xhttp.responseText + "<br>";
			}
		};
		xhttp.open("GET", "loginInfo?accessToken=" + accessToken, true);
		xhttp.send();
	}
</script>
</head>
<body>
	<div id="info"></div>
</body>

</html>