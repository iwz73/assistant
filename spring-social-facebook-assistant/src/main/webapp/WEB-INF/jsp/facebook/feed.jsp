<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>facebook feed</title>
<script
	src='<c:url value="/javascript/jquery-2.1.4/jquery-2.1.4.min.js"></c:url>'></script>
<script type="text/javascript">
	$(function() {
		$("#showFeeds").on("click", function() {
			var pageId = $("#pageId").val();
			$.ajax({
				url : '<c:url value="/facebook/getFeeds.json"></c:url>',
				method : 'GET',
				contentType : 'application/json; charset=UTF-8',
				data : {
					"pageId" : pageId
				}
			}).done(function(feeds, textStatus, jqXHR) {
				var info = $("#info")
				var table = $("<table>");
				table.attr("border", "1");
				var tr;
				var td;
				var id;
				var message;
				var created_time;
				var data = feeds["data"];
				for (i = 0, size = data.length; i < size; ++i) {
					tr = $("<tr>");
					id = data[i]["id"];
					message = data[i]["message"];
					created_time = data[i]["created_time"];
					td = $("<td>");
					td.text(id);
					tr.append(td);
					td = $("<td>");
					td.text(message);
					tr.append(td);
					td = $("<td>");
					td.text(created_time);
					tr.append(td);
					table.append(tr);
				}
				info.append(table);
				var paging = feeds["paging"];
				$("#more").attr("href", paging["next"]);
			});
		});
        $("#more").on("click", function() {
//             var url = $("#next").attr("href");
            $.ajax({
                url : $("#more").attr("href"),
                method : 'GET',
                contentType : 'application/json; charset=UTF-8'
            }).done(function(feeds, textStatus, jqXHR) {
                var info = $("#info")
                var table = $("<table>");
                table.attr("border", "1");
                var tr;
                var td;
                var id;
                var message;
                var created_time;
                var data = feeds["data"];
                for (i = 0, size = data.length; i < size; ++i) {
                    tr = $("<tr>");
                    id = data[i]["id"];
                    message = data[i]["message"];
                    created_time = data[i]["created_time"];
                    td = $("<td>");
                    td.text(id);
                    tr.append(td);
                    td = $("<td>");
                    td.text(message);
                    tr.append(td);
                    td = $("<td>");
                    td.text(created_time);
                    tr.append(td);
                    table.append(tr);
                }
                info.append(table);
                var paging = feeds["paging"];
                $("#previous").attr("href", paging["previous"]);
                $("#next").attr("href", paging["next"]);
            });
            return false;
        });
	});
</script>
</head>
<body>
	<select id="pageId">
		<option value="6815841748" selected="selected">Barack Obama</option>
	</select>
	<input id="showFeeds" type="button" value="show feeds">
	<hr>
	<div id="info"></div>
	<a href="#" id="more">more</a>
</body>

</html>