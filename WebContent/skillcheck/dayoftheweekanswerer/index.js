var xmlHttpRequest;
function calculate() {
	var messageElement = document.getElementById("date");
	var url = "index?date=" + messageElement.value+"&method=get";

	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = receive;
	xmlHttpRequest.open("GET", url, true);
	xmlHttpRequest.send(null);
	}
window.addEventListener("load", function() {
	var calculateButtonElement = document.getElementById("execute_button");
	calculateButtonElement.addEventListener("click",calculate, false);
}, false);
function receive() {
	if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		var response = JSON.parse(xmlHttpRequest.responseText);

		var echoMessageElement = document.getElementById("weekday");
		echoMessageElement.innerHTML = response.output;
	}
}