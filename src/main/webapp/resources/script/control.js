/**
 * 
 */

/*
	    
	    function drawGauge() {
	  	    var data = google.visualization.arrayToDataTable([
	          ['Label', 'Value'],
	          ['Current', Number('${sensor}')],
	          ['Average', Number('${average}')],
	          ['Peak', Number('${peak}')]
	        ]);

	        var options = {
	          width: 400, height: 120,
	          redFrom: 90, redTo: 100,
	          yellowFrom:75, yellowTo: 90,
	          minorTicks: 5
	        };

	        var chart = new google.visualization.Gauge(document.getElementById('gauge'));

	        chart.draw(data, options);

	        setInterval(function() {
	          //data.setValue(0, 1, 40 + Math.round(60 * Math.random()));
	          data.setValue(0, 1, Number("${sensor}"));
	          chart.draw(data, options);
	        }, 13000);
	        setInterval(function() {
	          data.setValue(1, 1, Number("${average}"));
	          chart.draw(data, options);
	        }, 5000);
	        setInterval(function() {
	          data.setValue(2, 1, Number("${peak}"));
	          chart.draw(data, options);
	        }, 26000);
	      }
*/