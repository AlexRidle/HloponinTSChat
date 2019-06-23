package coders;

import com.google.gson.Gson;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @author Paul Hloponin
 */
public class MessageDecoder implements Decoder.Text<String> {
    public static Gson gson = new Gson();

    @Override
    public String decode(String s) {
        return gson.fromJson(s, String.class);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
