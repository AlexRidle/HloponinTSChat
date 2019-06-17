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


    // Пользователь производит соединение
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
    public void onMessage(Session session, String message) {
        try {
            if (applyconnect) {
                connect.send(message);
//                session.getBasicRemote().sendText(message);
            } else register();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    public void sendEndpoint(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }
    // Регистрируем нового пользователя, передаем этот chatEndpoint и текст сообщения
    private void register() throws IOException {
        connect = new Connect(this, logger);
        connect.start();
        applyconnect = true;
    }

}
