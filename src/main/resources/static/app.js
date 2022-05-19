let stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    const socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/reservation', function (greeting) {
            var answer = JSON.parse(greeting.body);
            showGreeting(answer.sector, answer.places);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}


function reserveTicket(eventId, sector, placeN, placeK) {
    stompClient.send("/app/reserve", {}, JSON.stringify({
        'eventId': eventId,
        'sector': sector
    }));
}
function showGreeting(sector, placeK) {
    switch (sector) {
        case "A1":
            change0(placeK);
            break;
        case "A2":
            change1(placeK);
            break;
        case "B1":
            change2(placeK);
            break;
        case "B2":
            change3(placeK);
            break;
        case "B3":
            change4(placeK);
            break;
        case "Lounge":
            change5(placeK);
            break;
        default:
            alert('error')

    }
}
function change0(message) {
    $("#place0").text(message);
}
function change1(message) {
    $("#place1").text(message);
}
function change2(message) {
    $("#place2").text(message);
}
function change3(message) {
    $("#place3").text(message);
}
function change4(message) {
    $("#place4").text(message);
}
function change5(message) {
    $("#place5").text(message);
}
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();
    $("#reserve").click(function () {
        reserveTicket();
    });
});