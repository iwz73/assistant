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
	<h3>Users</h3>
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
	<h3>Relationships</h3>
	<button onclick="javascritp:usersSelfFollows('/users/self/follows');">/users/self/follows</button>
	<br>
	<button onclick="javascritp:usersSelfFollowedBy('/users/self/followed-by');">/users/self/followed-by</button>
	<br>
	<button onclick="javascritp:usersSelfRequestedBy('/users/self/requested-by');">/users/self/requested-by</button>
	<br>
	<input id="usersUserIdRelationship" type="text" value="3910988228"><button onclick="javascritp:usersUserIdRelationship();">/users/{user-id}/relationship</button>
	<br>
</body>
<script>
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