package endpoints;

import coders.MessageDecoder;
import coders.MessageEncoder;
import connect.Connect;
import message.Message;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

@ServerEndpoint(value = "/chat", decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
public class ChatEndpoint {

    private static Logger logger = Logger.getLogger("WebApp");
    private boolean applyconnect = false;
    private Session session = null;
    private Connect connect;


    // 1. Пользователь производит соединение
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    // Закрытие сокдинения
    @OnClose
    public void onClose(Session session) {
    }

    // Ошибка
    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    // Передаем сообщение в сокет на сервер
    @OnMessage
    public void onMessage(Session session, Message message) {
        try {
            if (applyconnect) {
                connect.send(message.toString());
                session.getBasicRemote().sendObject(message);
            } else register(message);
        } catch (IOException | EncodeException e) {
            logger.info(e.getMessage());
        }
    }

    public void sendEndpoint(Message message) {
        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            logger.info(e.getMessage());
        }
    }
    // Регистрируем нового пользователя, передаем этот chatEndpoint и текст сообщения
    private void register(Message message) throws IOException {
        connect = new Connect(this, message.getText());
        new Thread(connect).start();
        connect.send(message.toString());
        applyconnect = true;
    }

}
