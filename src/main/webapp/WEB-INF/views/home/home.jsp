<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/include/incHeader.jsp" %>

<script type="text/javascript">
var wsUri = "ws://localhost/websocket/echo";

function init() {
	output = document.getElementById("output");
}

function send_message() {
	websocket = new WebSocket(wsUri);
	websocket.onopen = function(evt) {
		onOpen(evt);
	};

	websocket.onmessage = function(evt) {
		onMessage(evt);
	};

	websocket.onerror = function(evt) {
		onError(evt);
	};
}

function onOpen(evt) {
	writeToScreen("connected to Endpoint");
	doSend(textID.value);
}

function onMessage(evt) {
	writeToScreen("message received: " + evt.data);
}

function onError(evt) {
	writeToScreen("error: " + evt.data);
}

function doSend(message) {
	writeToScreen("message sent: " + message);
	websocket.send(message);
}

function writeToScreen(message) {
	var pre = document.createElement("p");
	pre.style.wordWrap = "break-word";
	pre.innerHTML = message;

	output.appendChild(pre);
}

window.addEventListener("load", init, false);

</script>


<script type="text/javascript">
// http://pay114.cafe24.com/?c=1/5&uid=59
$(function() {
	$(document).ready(function() {
		Highcharts.setOptions({
			global: {
				useUTC: false
			}
		});

		var chart;
		$("#container").highcharts({
			chart: {
				type: 'spline',
				animation: Highcharts.svg,
				marginRight: 10,
				events: {
					load: function() {
						var series = this.series[0];
						var series2 = this.series[1];
						/* setInterval(function() {
							var x = (new Date()).getTime(),
							y = Math.random();
							z = Math.random();
							series.addPoint([x, y], false, true);
							series.addPoint([x, z], true, true);
						}, 1000); */
					}
				}
			},
			title: {
				text: '라이브 랜덤 데이터'
			},
			xAxis: {
				type: 'datetime',
				tickPixelInterval: 150
			},
			yAxis: [
				{
					title: {
						text: 'value1'
					},
					plotLines: [{
						value: 0,
						width: 1,
						color: '#808080'
					}]
				},
				{
					title: {
						text: 'value2'
					},
					plotLines: [{
						value: 0,
						width: 1,
						color: '#808080'
					}]
				}
			],
			//말풍선
			tooltip: {
				formatter: function() {
					return '<b>' + this.series.name + '</b><br />' +
					Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br />' +
					Highcharts.numberFormat(this.y, 2);
				}
			},
			//데이터 주석 - 하단
			legend: {
				//enabled: false
			},
			//차트 다운로드
			exporting: {
				//enabled: false
			},
			// 데이터
			series: [
				{
					name: '랜덤 데이터',
					data: (function() {
						var data = [],
						time = (new Date()).getTime(),
						i;

						for (i = -19; i <= 0; i++) {
							data.push({
								x: time + i * 1000,
								y: Math.random()
							});
						}

						return data;
					})()
				},
				{
					name: '랜덤 데이터',
					data: (function() {
						var data = [],
						time = (new Date()).getTime(),
						i;

						for (i = -19; i <= 0; i++) {
							data.push({
								x: time + i * 1000,
								y: Math.random()
							});
						}

						return data;
					})()
				}
			]
		});
	});
});
</script>


<div>
	<form action="">
		<input type="button" onclick="javascript:send_message();" value="SEND" />
		<input type="text" id="textID" name="message" />
	</form>
</div>

<div id="output"></div>


<div id="container" style="min-width: 728px; height: 400px; margin: 0 auto"></div>


<%@include file="/WEB-INF/views/include/incFooter.jsp" %>