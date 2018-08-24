var webSocket = null;
$(function () {
    test();
});

function test() {
    webSocket = new SockJS("/webSocket/demo");
    webSocket.onopen = function () {
        webSocket.send();
    };
    webSocket.onmessage = function (e) {
        if (e.data) {
            var data = e.data;
            $("#message").text(data);
        }

    };
    webSocket.onclose = function () {
        console.log('close run status socket');
    };

    webSocket.onerror = function () {
        console.error("链接出现异常，请检查服务器是否正常运行");
    };
}

function sendMsg() {
    if (webSocket.readyState == SockJS.OPEN) {
        var msg = $("#msg").val();
        webSocket.send(msg);
    } else {
        alert("连接失败!")
    }
}