package coders;

import com.google.gson.Gson;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author Paul Hloponin
 */
public class MessageEncoder implements Encoder.Text<String> {
    private static Gson gson = new Gson();

    @Override
    public String encode(String message) {
        return gson.toJson(message);
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
