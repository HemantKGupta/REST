<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test app</title>

<script type="text/javascript">
                var websocket;
                //var uri = 'ws://' + window.location.host + '/TestWebApp/websocket/simpleEchoMessage';
                var uri = 'ws://' + window.location.host + '/TestWebApp/hkg';
                //alert(uri);
                function connect()
                {
                    if ('WebSocket' in window) {
                        websocket = new WebSocket(uri);
                    } else if ('MozWebSocket' in window) {
                        websocket = new MozWebSocket(uri);
                    } else {
                        alert('WebSocket is not supported by this browser.');
                        return;
                    }
                    websocket.onopen = function() {
                        display('Connected to server.');
                    };

                    websocket.onclose = function() {
                        display('Disconnected from server');
                    };

                    websocket.onmessage = function(event) {
                        display('server says :'+event.data);
                    };
                }
                function send()
                {
                    var msg = document.getElementById('msg').value;
                    display('Browser Says :'+msg);
                    websocket.send(msg);
                }
                function display(msg) {
                    var screen = document.getElementById('screen');
                    var p = document.createElement('p');
                    //p.style.wordWrap = 'break-word';
                    p.appendChild(document.createTextNode(msg));
                    screen.appendChild(p);
                }
                connect();
        </script>

</head>
<body>
 <div>
      <div style="overflow-y: scroll; width:250px; height: 300px;" id="screen"></div>
      <!-- <input type="text" placeholder="Enter Message" id="msg"/>
      <button id="send" onclick="send();">Send</button> -->
 </div>
</body>
</html>