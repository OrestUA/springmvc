package guru.springframework.services.security;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;

/**
 * Created by YSkakun on 2/24/2017.
 */
public class LoginFailureEventHandler implements ApplicationListener<LoginFailureEvent> {
    @Override
    public void onApplicationEvent(LoginFailureEvent loginFailureEvent) {
        Authentication authentication = (Authentication)loginFailureEvent.getSource();
        System.out.println("LoginEvent failure for: " + authentication.getPrincipal());

    }
}
