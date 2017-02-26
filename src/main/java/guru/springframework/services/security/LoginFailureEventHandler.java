package guru.springframework.services.security;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Created by YSkakun on 2/24/2017.
 */
@Component
public class LoginFailureEventHandler implements ApplicationListener<LoginFailureEvent> {
    @Override
    public void onApplicationEvent(LoginFailureEvent loginFailureEvent) {
        Authentication authentication = (Authentication)loginFailureEvent.getSource();
        System.out.println("LoginEvent failure for: " + authentication.getPrincipal());

    }
}
