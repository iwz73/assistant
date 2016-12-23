<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>facebook social plugin</title>
<script>
	window.fbAsyncInit = function() {
		FB.init({
			appId : '1394401980571844',
			xfbml : true,
			version : 'v2.8'
		});
	};
</script>
</head>
<body>
	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {
				return;
			}
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/zh_TW/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	<div class="fb-like" data-layout="button_count"></div>
	<div class="fb-share-button" data-layout="button_count"></div>
	<div class="fb-send"></div>
	<div class="fb-follow" data-href="https://www.facebook.com/zuck"
		data-layout="button_count"></div>
	<hr>
	<div class="fb-comments"></div>
	<hr>
	<div class="fb-post"
		data-href="https://www.facebook.com/FacebookDevelopers/posts/10151471074398553"
		data-width="500px"></div>
	<hr>
	<div class="fb-video"
		data-href="https://www.facebook.com/facebook/videos/10153231379946729/"
		data-width="500px" data-allowfullscreen="false" data-autoplay="true"></div>
	<hr>
	<div class="fb-page" data-href="https://www.facebook.com/facebook"
		data-width="500px" data-show-posts="true"></div>

</body>
</html>