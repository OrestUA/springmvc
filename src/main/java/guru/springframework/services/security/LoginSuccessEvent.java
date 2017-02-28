package guru.springframework.services.security;

import org.springframework.context.ApplicationEvent;

/**
 * Created by YSkakun on 2/28/2017.
 */
public class LoginSuccessEvent extends ApplicationEvent {

    public LoginSuccessEvent(Object source) {
        super(source);
    }
}
