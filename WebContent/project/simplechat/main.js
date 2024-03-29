var xmlHttpRequest;

function sendSendRequest() {
	var messageElement = document.getElementById("message");
	var url = "send";
	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = checkSendRequest;
	xmlHttpRequest.open("POST", url, true);
	xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttpRequest.send("message=" + encodeURIComponent(messageElement.value));
}

function receiveSendResponse() {
	//var response = eval("(" + xmlHttpRequest.responseText + ")");
}

function checkSendRequest() {
	if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		receiveSendResponse();
	}
}

function sendUpdateRequest() {
	var url = "update";
	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = checkUpdateRequest;
	xmlHttpRequest.open("GET", url, true);
	xmlHttpRequest.send(null);
}

function receiveUpdateResponse() {
	var response = JSON.parse(xmlHttpRequest.responseText);
	var nameElement = document.getElementById("name");
	nameElement.innerHTML = response.user.name;
	var statementListElement = document.getElementById("statement_list");
	statementListElement.innerHTML = "";
	for (var i = 0; i < response.statementList.length; i++) {
		var statement = response.statementList[i];
		var statementElement = document.createElement("tr");
		statementListElement.appendChild(statementElement);
		var userElement = document.createElement("td");
		statementElement.appendChild(userElement);
		userElement.innerHTML = statement.user.name + ":";
		var messageElement = document.createElement("td");
		statementElement.appendChild(messageElement);
		messageElement.innerHTML = statement.message;
	}
}

function checkUpdateRequest() {
	if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		receiveUpdateResponse();
	}
}

window.addEventListener("load", function() {
	var sendButtonElement = document.getElementById("send_button");
	sendButtonElement.addEventListener("click", sendSendRequest, false);
	sendUpdateRequest();
	setInterval(sendUpdateRequest, 1000);
}, false);