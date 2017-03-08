package guru.springframework.services.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Fudjitsu on 08.03.2017.
 */
@Component
public class TextMessageListener {

    @JmsListener(destination = "text.messagequeue")
    public void onMessage(String msg) {
        System.out.println("======================MESSAGE=====================");
        System.out.println("msg = [" + msg + "]");
    }
}
