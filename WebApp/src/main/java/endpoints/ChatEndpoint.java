package endpoints;

import coders.MessageDecoder;
import coders.MessageEncoder;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import message.Message;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@ServerEndpoint(value = "/chat", decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
public class ChatEndpoint {

    private static Logger logger = Logger.getLogger("WebApp");

    private Session session = null;
    private static List<javax.websocket.Session> sessionList = new LinkedList<>();
    @Getter
    @Setter

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        sessionList.add(session);
    }

    @OnClose
    public void onClose(Session session){
        sessionList.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable){
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, Message msg){
        sessionList.forEach(s->{
            try {
                s.getBasicRemote().sendObject(msg);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        });
    }

}
