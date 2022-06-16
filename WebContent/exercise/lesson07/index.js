var xmlHttpRequest;
function calculate() {
var messageElement = document.getElementById("input1");
var messageElement2 = document.getElementById("input2");

var url = "index?input1=" + messageElement.value + "&input2=" + messageElement2.value +"&method=get";

xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange = receive;
xmlHttpRequest.open("GET", url, true);
xmlHttpRequest.send(null);
}

window.addEventListener("load", function() {
	var calculateButtonElement = document.getElementById("calculate_button");
	calculateButtonElement.addEventListener("click",calculate, false);
}, false);
function receive() {
	if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		var response = JSON.parse(xmlHttpRequest.responseText);

		var echoMessageElement = document.getElementById("output");
		echoMessageElement.innerHTML = response.output;
	}
}