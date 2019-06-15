package message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String text;

    @Override
    public String toString() {
        return name + " : " + text;
    }
}
