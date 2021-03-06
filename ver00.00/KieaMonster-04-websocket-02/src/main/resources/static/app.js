var stompClient = null;

function _setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	} else {
		$("#conversation").hide();
	}
	$("#greetings").html("");
}

function _showGreeting(message) {
	$("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function connect() {
	// endpoint
	var socket = new SockJS('/v0.2/gs-guide-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		console.log('Connected: ' + frame);
		_setConnected(true);
		// recv subscribe
		stompClient.subscribe('/topic/greetings', function (greeting) {
			_showGreeting(JSON.parse(greeting.body).content);
		});
	});
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	console.log("Disconnected");
	_setConnected(false);
}

function sendName() {
	// send to controller
	stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

// $(document).ready(...)
$(function () {
	$("form").on('submit', function (e) {
		e.preventDefault();
	});
	$("#connect").click(function() { connect(); });
	$("#disconnect").click(function() { disconnect(); });
	$("#send").click(function() { sendName(); });
	$("#conversation").hide();
});

