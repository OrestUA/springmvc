package guru.springframework.services.security;

import org.springframework.context.ApplicationEvent;

/**
 * Created by YSkakun on 2/24/2017.
 */
public class LoginFailureEvent extends ApplicationEvent {

    public LoginFailureEvent(Object source) {
        super(source);
    }
}