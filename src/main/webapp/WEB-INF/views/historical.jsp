<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Historical</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidemenub.css" type="text/css"></link>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script src="http://cdn2.crunchify.com/wp-content/uploads/code/jquery.sparkline.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/control.js"></script>
	<script>
		var url = location.href.replace("/historical","") + "/sensor";
		var xmlhttp = new XMLHttpRequest();
	
		xmlhttp.onreadystatechange=function() {
		    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			    var arr = JSON.parse(xmlhttp.responseText);
			    var date;
			    var options = {
			    	    year: "numeric",
			    	    month: "2-digit",
			    	    day: "numeric",
			    	    hour: "2-digit", 
			    	    minute: "2-digit"
			    	};
			    var tr = "<table class='responstable'>";
			    tr += "<tr><th>Date</th><th>Item</th><th>State</th></tr>";
		        for (var i = 0; i < arr.length; i++) {
		            tr += "<tr>";
		            date = new Date(arr[i].date);
		            tr += ("<td>" + date.toLocaleDateString("en-au", options) + "</td>");
		            tr += ("<td>" + arr[i].item + "</td>");
		            tr += ("<td>" + arr[i].state + "</td>");
		            tr += "</tr>";
		        }
		        tr += "</table>";
	            document.getElementById("historicaltable").innerHTML = tr;
		    }
		}
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
	</script>
</head>
<body>
	<%@include  file="/resources/menu.html" %>

	<div id="historicaltable" style="width: 100%;" align="center"></div>
</body>
</html>