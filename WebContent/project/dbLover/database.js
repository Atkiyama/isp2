var xmlHttpRequest;

function searchRequest(){
    updateRequest();
    var startMsec = new Date();
    while (new Date() - startMsec < 200);
    var url = "search";
    var searchElement = document.getElementById("search");
    xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = receiveSearchRequest;
    xmlHttpRequest.open("POST", url, true);
    xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttpRequest.send("search=" + encodeURIComponent(searchElement.value));
}

function receiveSearchRequest(){
  if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
    var response = JSON.parse(xmlHttpRequest.responseText);
    var searchResultElement = document.getElementById("searchResult");
    var str;
    str = "<details open> <summary>検索結果</summary> <p>"
    for(var i = 0; i < response.search.length; i++){
	     for(var j = 0; j < response.search[i].length; j++){
	        str += response.search[i][j];
	        str += "　";
	     }
	     str += "<br>";
    }
    str += "</p> </details>";
    searchResultElement.innerHTML = str;
  }
}

function sortRequest(){
  updateRequest();
  var startMsec = new Date();
  while (new Date() - startMsec < 200);
  var url = "sort";
  var sortTypeElement = document.getElementById("sort_type");
  var sortElement = document.getElementById("sort");
  var sortType = sortTypeElement.sort_type;
	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = receiveSortRequest;
	xmlHttpRequest.open("POST", url, true);
  xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttpRequest.send("sortType="+sortType.value+"&sort="+sortElement.value);
}

function receiveSortRequest(){
  if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
    createRequest("create");
  }
}

function saveRequest(){
  updateRequest();
  var startMsec = new Date();
  while (new Date() - startMsec < 200);
  var url = "save";
  var nameElement = document.getElementById("name");
	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = receiveSaveRequest;
	xmlHttpRequest.open("POST", url, true);
  xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttpRequest.send("name="+nameElement.value);
}

function receiveSaveRequest(){
  if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
    var response = JSON.parse(xmlHttpRequest.responseText);
    if(Number(response.isError) == 0){
      alert("CSVへの保存が成功しました");
    }else if(Number(response.isError) == 1){
      alert("保存に失敗しました。\n同一名のファイルが存在しています。保存するCSVファイル名を変更して再度試してください。");
    }
  }
}

function updateRequest(){
    var url = "update";
    xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("POST", url, true);
    xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    var columnElement = document.getElementsByClassName("column");
    var rowElement = document.getElementsByClassName("row");
    var datas = "update=";
    for(var i = 0; i < columnElement.length; i++){
      if(i != 0){
	     datas += ";;";
     }
	      for(var j = 0; j < rowElement.length; j++){
          if(j != 0){
            datas += ",,";
          }
          datas += encodeURIComponent(document.getElementById(String(i)+","+String(j)).value);
	      }
    }
    xmlHttpRequest.send(datas);
}

function createRequest(type){
    var url = "create";
    xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = receiveCreateRequest;
    xmlHttpRequest.open("POST", url, true);
    xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttpRequest.send("type="+type);
}

function receiveCreateRequest(){
  if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
    var response = JSON.parse(xmlHttpRequest.responseText);
    var databaseElement = document.getElementById("database");
    databaseElement.innerHTML = "";
    var str;
    var height = Number(response.height);
    var width = Number(response.width);
    if(Number(response.isSelf) == 1){
	     for(var i = 0; i < height; i++){
          str = "";
	        for(var j = 0; j < width; j++){
		          str += "<input type=\"text\" ";
              if(i == 0 && j == 0){
                str += "class=\"row column\" ";
              }else if(i == 0){
                str += "class=\"row\" ";
              }else if(j == 0){
                str += "class=\"column\" ";
              }
              str += "id=\""+String(i)+","+String(j)+"\" />";
	        }
	        str += "<br>";
          databaseElement.innerHTML += str;
	     }
    }else if(Number(response.isSelf) == 0){
      for(var i = 0; i < height; i++){
         str = "";
         for(var j = 0; j < width; j++){
             str += "<input type=\"text\" ";"id=\""+String(i)+","+String(j)+"\" value=\""+response.DB[i][j]+"\"/>";
             if(i == 0 && j == 0){
               str += "class=\"row column\" ";
             }else if(i == 0){
               str += "class=\"row\" ";
             }else if(j == 0){
               str += "class=\"column\" ";
             }
             str += "id=\""+String(i)+","+String(j)+"\" value=\""+response.DB[i][j]+"\"/>";
         }
         str += "<br>";
         databaseElement.innerHTML += str;
      }
    }
    databaseElement.innerHTML += "<br>";
  }
}

function columnPlus(){
  updateRequest();
  createRequest("columnPlus");
}

function columnMinus(){
  updateRequest();
  createRequest("columnMinus");
}

function rowPlus(){
  updateRequest();
  createRequest("rowPlus");
}

function rowMinus(){
  updateRequest();
  createRequest("rowMinus");
}

window.addEventListener("load", function() {
    var searchButtonElement = document.getElementById("search_button");
    searchButtonElement.addEventListener("click", searchRequest, false);
    var sortButtonElement = document.getElementById("sort_button");
    sortButtonElement.addEventListener("click", sortRequest, false);
    var saveButtonElement = document.getElementById("save_button");
    saveButtonElement.addEventListener("click", saveRequest, false);
    var columnPlusButtonElement = document.getElementById("column_plus_button");
    columnPlusButtonElement.addEventListener("click", columnPlus, false);
    var columnMinusButtonElement = document.getElementById("column_minus_button");
    columnMinusButtonElement.addEventListener("click", columnMinus, false);
    var rowPlusButtonElement = document.getElementById("row_plus_button");
    rowPlusButtonElement.addEventListener("click", rowPlus, false);
    var rowMinusButtonElement = document.getElementById("row_minus_button");
    rowMinusButtonElement.addEventListener("click", rowMinus, false);
    createRequest("create");
}, false);
