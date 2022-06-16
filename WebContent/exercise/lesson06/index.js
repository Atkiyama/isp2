function calculate() {
	var input1Element = document.getElementById("input1");
	var input2Element = document.getElementById("input2");

	var input1 = parseInt(input1Element.value);
	var input2 = parseInt(input2Element.value);
	var output = input1 +input2;

	var outputElement = document.getElementById("output");
	outputElement.innerHTML = output;
}

window.addEventListener("load", function() {
	var calculateButtonElement = document.getElementById("calculate_button");
	calculateButtonElement.addEventListener("click",calculate, false);
}, false);
