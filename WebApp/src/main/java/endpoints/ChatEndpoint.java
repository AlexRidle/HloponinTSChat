package endpoints;

import coders.MessageDecoder;
import coders.MessageEncoder;
import connect.Connect;

import javax.websocket.*;//Do not import all classes from package. Import only what you need.
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Paul Hloponin
 */
@ServerEndpoint(value = "/chat", decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
public class ChatEndpoint {

    private static Logger logger = Logger.getLogger("WebApp");
    private boolean applyconnect = false;//Not clear why do we need this variable.
    private Session session = null;//Unnecessary variable.
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
                connect.sendToServer(message);
            } else register();//Formatting. Better to use brackets {} to increase code readability.
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    //This two methods (register and sendToWeb) must be located in different service class.
    public void sendToWeb(String message) {
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
        applyconnect = true;//Formatting. New word in variable must be written from capital letter.
    }

}
