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
	<h3>User</h3>
	<button onclick="javascritp:usersSelf('/users/self');">/users/self</button>
	<br>
	<input id="usersUserId" type="text" value="3910988228"><button onclick="javascritp:usersUserId();">/users/{user-id}</button>
	<br>
	<button onclick="javascritp:usersSelfMediaRecent('/users/self/media/recent');">/users/self/media/recent</button>
	<br> 
	<input id="usersUserIdMediaRecent" type="text" value="3910988228"><button onclick="javascritp:usersUserIdMediaRecent();">/users/{user-id}/media/recent</button>
	<br>
	<button onclick="javascritp:usersSelfMediaLiked('/users/self/media/liked');">/users/self/media/liked</button>
	<br>
	<h3>Relationship</h3>
	<button onclick="javascritp:usersSelfFollows('/users/self/follows');">/users/self/follows</button>
	<br>
	<button onclick="javascritp:usersSelfFollowedBy('/users/self/followed-by');">/users/self/followed-by</button>
	<br>
	<button onclick="javascritp:usersSelfRequestedBy('/users/self/requested-by');">/users/self/requested-by</button>
	<br>
	<input id="usersUserIdRelationship" type="text" value="3910988228"><button onclick="javascritp:usersUserIdRelationship();">/users/{user-id}/relationship</button>
	<br>
	<h3>Media</h3>
	<input id="mediaMediaId" type="text" value="1338844418916456943_2540988167"><button onclick="javascritp:mediaMediaId();">/media/{media-id}</button>
	<br>
	<h3>Comment</h3>
	<input id="mediaMediaIdComments" type="text" value="1338844418916456943_2540988167"><button onclick="javascritp:mediaMediaIdComments();">/media/{media-id}/comments</button>
	<br>
	<h3>Like</h3>	
	<input id="mediaMediaIdLikes" type="text" value="1338844418916456943_2540988167"><button onclick="javascritp:mediaMediaIdLikes();">/media/{media-id}/likes</button>
	<br>
</body>
<script>
	function mediaMediaIdLikes() {
		var mediaId = document.getElementById("mediaMediaIdLikes").value
		var endpoint = "/media/" + mediaId + "/likes";
		showEndpointData(endpoint);
	}

	function mediaMediaIdComments() {
		var mediaId = document.getElementById("mediaMediaIdComments").value
		var endpoint = "/media/" + mediaId + "/comments";
		showEndpointData(endpoint);
	}

	function mediaMediaId() {
		var mediaId = document.getElementById("mediaMediaId").value
		var endpoint = "/media/" + mediaId;
		showEndpointData(endpoint);
	}

	function usersUserIdRelationship() {
		var userId = document.getElementById("usersUserIdRelationship").value
		var endpoint = "/users/" + userId + "/relationship";
		showEndpointData(endpoint);
	}
	
	function usersSelfRequestedBy(endpoint) {
		showEndpointData(endpoint);
	}
	
	function usersSelfFollowedBy(endpoint) {
		showEndpointData(endpoint);
	}
	
	function usersSelfFollows(endpoint) {
		showEndpointData(endpoint);
	}

	function usersSelfMediaLiked(endpoint) {
		showEndpointData(endpoint);
	}

	function usersUserIdMediaRecent() {
		var userId = document.getElementById("usersUserIdMediaRecent").value
		var endpoint = "/users/" + userId + "/media/recent";
		showEndpointData(endpoint);
	}

	function usersSelfMediaRecent(endpoint) {
		showEndpointData(endpoint);
	}

	function usersUserId() {
		var userId = document.getElementById("usersUserId").value
		var endpoint = "/users/" + userId;
		showEndpointData(endpoint);
	}

	function usersSelf(endpoint) {
		showEndpointData(endpoint);
	}

	function showEndpointData(endpoint) {
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
						"_blank");
	}
</script>
</html>