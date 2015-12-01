<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Sensor Dashboard</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidemenub.css" type="text/css"></link>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script src="http://cdn2.crunchify.com/wp-content/uploads/code/jquery.sparkline.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/control.js"></script>
    <script type="text/javascript">
		
    	var current = 0;
    	var historicdata = "0";
		function drawDonutChart() {
		
			var data = google.visualization.arrayToDataTable([
			    ['Effort', 'Amount given'],
			    ['Little',     10],
			    ['Aparthy',     30],
			    ['Not Bad',     60],
			  ]);
		
			var options = {
			    pieHole: 0.5,
			    pieSliceTextStyle: {
			      color: 'black',
			    },
			    legend: 'none'
			  };
		
			var chart = new google.visualization.PieChart(document.getElementById('donut_single'));
			chart.draw(data, options);
		}
	
		
		function getCurrent(){
			var xmlhttp = new XMLHttpRequest();
			var url = location.href + "/sensor/current";

			xmlhttp.onreadystatechange=function() {
			    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				    var arr = JSON.parse(xmlhttp.responseText);
					current = Number(arr.state);
					
			    }
			}
			xmlhttp.open("GET", url, true);
			xmlhttp.send();
			historicdata += current.toString();
			return current;
		}
		
		function drawGauge() {

			
		    var data = google.visualization.arrayToDataTable([
		      ['Label', 'Value'],
		      ['Current', getCurrent()],
		      ['Average', 64],
		      ['Peak', 97]
		    ]);
		
		    var options = {
		      width: 400, height: 120,
		      redFrom: 90, redTo: 100,
		      yellowFrom:70, yellowTo: 89,
		      minorTicks: 5
		    };
		
		    var chart = new google.visualization.Gauge(document.getElementById('gauge'));
		
		    chart.draw(data, options);
		
		    setInterval(function() {
			    data.setValue(0, 1, getCurrent());
			    chart.draw(data, options);
			    $("#history").sparkline(historicdata, {
                    type : 'line',
                    width : '300',
                    height : '100',
                    fillColor : '#eeeeee'
                });
			    }, 5000);
/*		    setInterval(function() {
			   data.setValue(1, 1, Number("${average}"));
			      chart.draw(data, options);
			    }, 5000);
		    setInterval(function() {
			   data.setValue(2, 1, Number("${peak}"));
			      chart.draw(data, options);
			    }, 26000);*/
		  }	    

      
    	//google.load("visualization", "1", {packages:["corechart"]});
	    //google.setOnLoadCallback(drawDonutChart);
	    
	    google.load("visualization", "1", {packages:["gauge"]});
	    google.setOnLoadCallback(drawGauge);

    </script>
  </head>
<body>
	<%@include  file="/resources/menu.html" %>


	<!-- <div id="donut_single" style="width: 400px; height: 400px;"></div>-->

	<div id="gauge" style="width: 100%;" align="center"></div>
	
	<div style="width:100%" align="center">
		<span id="history">.</span>
	</div>
</body>
</html>
