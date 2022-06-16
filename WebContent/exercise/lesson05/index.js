window.addEventListener("load", function () {
    var secondHandOfLength = 100; //秒針の長さ
    var timeInstance = new Date();//Dateオブジェクトのインスタンス生成
    var second = timeInstance.getSeconds();//秒数取得
    var radius = second/30 * Math.PI;//角度取得
    var xPoint = Math.round(Math.sin(radius) * secondHandOfLength) //x座標取得
    var yPoint = Math.round(Math.cos(radius) * secondHandOfLength) //y座標取得
    document.write(second + "秒のときの秒針の位置は(" + xPoint + ", " + yPoint + ")です");
}, false);
