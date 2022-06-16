var user = null;

function login() {
	//console.log("Login");
	
	hideLoginDialog();
	
	var loginDialogUsernameFieldElement = document.getElementById("login_dialog-username_field");
	
	user = {name: loginDialogUsernameFieldElement.value};
	
	var headerUsernameElement = document.getElementById("header-username");
	var headerLoginButtonAreaElement = document.getElementById("header-login_button_area");
	var headerLogoutButtonAreaElement = document.getElementById("header-logout_button_area");
	headerUsernameElement.innerHTML = user.name;
	headerLoginButtonAreaElement.style.display = "none";
	headerLogoutButtonAreaElement.style.display = "block";
}

function logout() {
	//console.log("Logout");	

	showLogoutDialog();
	
	user = null;
	
	var headerUsernameElement = document.getElementById("header-username");
	var headerLoginButtonAreaElement = document.getElementById("header-login_button_area");
	var headerLogoutButtonAreaElement = document.getElementById("header-logout_button_area");
	headerUsernameElement.innerHTML = "?";
	headerLoginButtonAreaElement.style.display = "none";
	headerLogoutButtonAreaElement.style.display = "none";
}

function showLoginDialog() {
	//console.log("showLoginDailog");	
	
	var headerLoginButtonAreaElement = document.getElementById("header-login_button_area");
	headerLoginButtonAreaElement.style.display = "none";
    
	var loginDialogElement = document.getElementById("login_dialog");
    loginDialogElement.style.visibility = "visible";
}

function hideLoginDialog() {
	//console.log("hideLoginDialog");
	
	var headerLoginButtonAreaElement = document.getElementById("header-login_button_area");
	headerLoginButtonAreaElement.style.display = "block";
    
    var loginDialogElement = document.getElementById("login_dialog");
    loginDialogElement.style.visibility = "hidden";
}

function showLogoutDialog() {
	//console.log("showLogoutDialog");
	
	var logoutDialogElement = document.createElement("div");
	document.body.appendChild(logoutDialogElement);
	logoutDialogElement.setAttribute("id", "logout_dialog");
	
	var logoutDialogDivElement1 = document.createElement("div");
	logoutDialogElement.appendChild(logoutDialogDivElement1);
	logoutDialogDivElement1.innerHTML = "<span id=\"logout_dialog-username\">" + user.name + "</span>さんはログアウトしました．";
	
	var logoutDialogDivElement2 = document.createElement("div");
	logoutDialogElement.appendChild(logoutDialogDivElement2);

	var logoutDialogCloseButtonAreaElement = document.createElement("div");
	logoutDialogDivElement2.appendChild(logoutDialogCloseButtonAreaElement);
	logoutDialogCloseButtonAreaElement.setAttribute("id", "logout_dialog-close_button_area");
	
	var logoutDialogCloseButtonElement = document.createElement("button");
	logoutDialogCloseButtonAreaElement.appendChild(logoutDialogCloseButtonElement);
	logoutDialogCloseButtonElement.setAttribute("id", "logout_dialog-close_button");
	logoutDialogCloseButtonElement.setAttribute("type", "button");
	logoutDialogCloseButtonElement.innerHTML = "クローズ";
	logoutDialogCloseButtonElement.addEventListener("click", hideLogoutDialog, false);
}

function hideLogoutDialog() {
	//console.log("hideLogoutDialog");	
	
	var headerLoginButtonAreaElement = document.getElementById("header-login_button_area");
	headerLoginButtonAreaElement.style.display = "block";
	
	var logoutDialogElement = document.getElementById("logout_dialog");
	
	document.body.removeChild(logoutDialogElement);
}

window.addEventListener("load", function() {
	var loginDialogElement = document.getElementById("login_dialog");
	loginDialogElement.style.visibility = "hidden";
	
	var headerUsernameElement = document.getElementById("header-username");
	var headerLoginButtonAreaElement = document.getElementById("header-login_button_area");
	var headerLogoutButtonAreaElement = document.getElementById("header-logout_button_area");
	
    if(user == null) {
		headerUsernameElement.innerHTML = "?";
		headerLoginButtonAreaElement.style.display = "block";
		headerLogoutButtonAreaElement.style.display = "none";
	}
	else {
		headerUsernameElement.innerHTML = user.name;
		headerLoginButtonAreaElement.style.display = "none";
		headerLogoutButtonAreaElement.style.display = "block";
	}
	
    var headerLoginButtonElement = document.getElementById("header-login_button");
    headerLoginButtonElement.addEventListener("click", showLoginDialog, false);
    
    var headerLogoutButtonElement = document.getElementById("header-logout_button");
    headerLogoutButtonElement.addEventListener("click", logout, false);
        
    var loginDialogLoginButtonElement = document.getElementById("login_dialog-login_button");
    loginDialogLoginButtonElement.addEventListener("click", login, false);
    
    var loginDialogCancelButtonElement = document.getElementById("login_dialog-cancel_button");
    loginDialogCancelButtonElement.addEventListener("click", hideLoginDialog, false);
}, false);
