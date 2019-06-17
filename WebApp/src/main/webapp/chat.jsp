<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>WebChatApp</title>
    <style>
        .chatbox {
            display: none;
        }

        .messages {
            background-color: cadetblue;
            width: 500px;
            padding: 15px;
        }

        .messages .msg {
            background-color: aliceblue;
            border-radius: 5px;
            margin-bottom: 5px;
            overflow: hidden;
        }

        .messages .msg .text {
            padding: 10px;
        }

        textarea.msg {
            width: 530px;
            padding: 15px;
            resize: none;
            border: none;
            box-shadow: 2px 2px 5px 0 inset;
        }

    </style>
</head>
<body>
<h1>ChatBox</h1>

<div class="startbox">
    <button id="start"> start</button>
</div>

<div class="chatbox">
    <textarea class="msg"></textarea>
    <div class="messages"></div>
</div>
<script>
    let chatUnit = {
        init() {
            this.startBox = document.querySelector(".startbox"); // Инициализация класса startbox
            this.chatBox = document.querySelector(".chatbox"); // Инициализация класса chatbox

            this.statrButton = this.startBox.querySelector("button"); // Инициализация кнопки

            this.msgTextArea = this.chatBox.querySelector("textarea"); // Инициализация textarea
            this.chatBoxContainer = this.chatBox.querySelector(".messages");

            this.bindEvents();  // Обработчик событий
        },

        bindEvents() {
            this.statrButton.addEventListener("click", ev => this.openSocket()); // При нажатии на кнопку происходит открытие сокета
            this.msgTextArea.addEventListener("keyup", ev => {
                if (ev.ctrlKey && ev.keyCode === 13) {
                    ev.preventDefault();
                    this.send();
                }
            }); // При нажатии на кнопку ctrl передаем в msgTextArea value
        },

        send(){ // Вызывает метод sendMessage и передает в него параметры
            this.sendMessage(this.msgTextArea.value);
        },

        sendMessage(msg) { // Отправляет сообщение
            this.msgTextArea.value = "";
            this.ws.send(msg);
        },

        onMessage(msg) { // Получает сообщение и выводит его на веб
            let msgBlock = document.createElement("div");
            msgBlock.className = "msg";

            let textBlock = document.createElement("div");
            textBlock.className = "text";
            textBlock.innerText = msg;

            msgBlock.appendChild(textBlock);

            this.chatBoxContainer.prepend(msgBlock);
        },

        openSocket() {
            this.ws = new WebSocket("ws://localhost:8080/web/chat"); // По нажатию открыли сокет
            this.ws.onopen = () => this.onOpenSocket();

            this.ws.onmessage = (ev) => this.onMessage(JSON.stringify(ev.data));
            this.ws.onclose = () => this.onClose();

            this.startBox.style.display = "none"; // После соединения прячем кнопку
            this.chatBox.style.display = "block"; // После соединения непрячем chatbox
        },

        onOpenSocket() {

        },

        onClose() {

        },
    };

    window.addEventListener("load", ev => chatUnit.init()); // ? При любом событии, при загрузке запускать метод init

</script>
</body>
</html>