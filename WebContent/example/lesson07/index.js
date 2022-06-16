var xmlHttpRequest;

function sendWithGetMethod() {
	var messageElement = document.getElementById("message");
	
	var url = "echo?message=" + messageElement.value + "&method=get";

	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = receive;
	xmlHttpRequest.open("GET", url, true);
	xmlHttpRequest.send(null);
}

function sendWithPostMethod() {
	var messageElement = document.getElementById("message");
	
	var url = "echo";
	
	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = receive;
	xmlHttpRequest.open("POST", url, true);
	xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttpRequest.send("message=" + messageElement.value + "&method=post");
}

function sendWithPutMethod() {
	var messageElement = document.getElementById("message");

	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = receive;
	xmlHttpRequest.open("PUT", "echo", true);
	xmlHttpRequest.send("message=" + messageElement.value + "&method=put");
}

function sendWithDeleteMethod() {
	var messageElement = document.getElementById("message");

	var url = "echo?message=" + messageElement.value + "&method=delete";

	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = receive;
	xmlHttpRequest.open("DELETE", url, true);
	xmlHttpRequest.send(null);
}

function receive() {
	if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		var response = JSON.parse(xmlHttpRequest.responseText);
		
		var echoMessageElement = document.getElementById("echo_message");
		echoMessageElement.innerHTML = response.message + "(" + response.method + ")";
	}
}

window.addEventListener("load", function() {
    var getButtonElement = document.getElementById("get_button");
    getButtonElement.addEventListener("click", sendWithGetMethod, false);
    
    var postButtonElement = document.getElementById("post_button");
    postButtonElement.addEventListener("click", sendWithPostMethod, false);
        
    var putButtonElement = document.getElementById("put_button");
    putButtonElement.addEventListener("click", sendWithPutMethod, false);
    
    var deleteButtonElement = document.getElementById("delete_button");
    deleteButtonElement.addEventListener("click", sendWithDeleteMethod, false);
}, false);