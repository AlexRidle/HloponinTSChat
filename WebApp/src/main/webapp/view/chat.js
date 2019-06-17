var sendMessage = document.querySelector("send");
var inputMessage = document.querySelector("input");



window.onload = function () {
    socket = new WebSocket("ws://localhost:8080/web/chat");
    // открытие соединения
    socket.onopen = function (ev) {
    };
    // закрытие соединения
    socket.onclose = function (ev) {

    };
    // получение данных
    socket.onmessage = function (ev) {
        let msgBlock = document.createElement("div");
        msgBlock.className = "msg";

        let fromBlock = document.createElement("div");
        fromBlock.className = "from";
        fromBlock.innerText = msg.name;

        let textBlock = document.createElement("div");
        textBlock.className = "text";
        textBlock.innerText = msg.text;

        msgBlock.appendChild(fromBlock);
        msgBlock.appendChild(textBlock);

        this.chatBoxContainer.appendChild(msgBlock);
    };
    // возникновение ошибки
    socket.onerror = function (ev) {

    }
};

// let chatUnit = {
//     init() {
//         this.startBox = document.querySelector(".start"); // Инициализация класса start
//         this.chatBox = document.querySelector(".chatbox"); // Инициализация класса chatbox
//
//         this.nameInput = this.startBox.querySelector("input");
//         this.statrButton = this.startBox.querySelector("button"); // Инициализация кнопки
//
//         this.msgTextArea = this.chatBox.querySelector("textarea"); // Инициализация textarea
//         this.chatBoxContainer = this.chatBox.querySelector(".messages");
//
//         this.bindEvents();  // Обработчик событий
//     },
//
//     bindEvents() {
//         this.statrButton.addEventListener("click", ev => this.openSocket()); // При нажатии на кнопку происходит открытие сокета
//         this.msgTextArea.addEventListener("keyup", ev => {
//             if (ev.ctrlKey && ev.keyCode == 13) {
//                 ev.preventDefault();
//                 this.send(this.msgTextArea.value);
//             }
//         }); // При нажатии на кнопку ctrl передаем в msgTextArea value
//     },
//
//     send() { // Вызывает метод sendMessage и передает в него параметры
//         this.sendMessage({
//             name: this.name,
//             text: this.msgTextArea.value
//         });
//     },
//
//     onOpenSocket() {
//
//     },
//
//     onMessage(msg) { // Получает сообщение и выводит его на веб
//         let msgBlock = document.createElement("div");
//         msgBlock.className = "msg";
//
//         let fromBlock = document.createElement("div");
//         fromBlock.className = "from";
//         fromBlock.innerText = msg.name;
//
//         let textBlock = document.createElement("div");
//         textBlock.className = "text";
//         textBlock.innerText = msg.text;
//
//         msgBlock.appendChild(fromBlock);
//         msgBlock.appendChild(textBlock);
//
//         this.chatBoxContainer.appendChild(msgBlock);
//     },
//
//     onClose() {
//
//     },
//
//     sendMessage() { // Отправляет сообщение
//         this.onMessage({name: "I'm", text: msg.text})
//         this.msgTextArea.value = "";
//         this.ws.send(JSON.stringify(msg));
//     },
//
//     openSocket() {
//         this.ws = new WebSocket("ws://localhost:8080/web/chat"); // По нажатию открыли сокет
//         this.ws.onopen = () => this.onOpenSocket();
//         this.ws.onmessage = (ev) => this.onMessage(JSON.parse(ev.data));
//         this.ws.onclose = () => this.onClose();
//
//         this.name = this.nameInput.value;
//         this.startBox.style.display = "none"; // После соединения прячем кнопку
//         this.chatBox.style.display = "block"; // После соединения непрячем chatbox
//     },
//
// };
//
// window.addEventListener("load", ev => chatUnit.init()); // ? При любом событии, при загрузке запускать метод init
