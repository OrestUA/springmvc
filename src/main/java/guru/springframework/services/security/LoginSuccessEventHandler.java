package guru.springframework.services.security;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Created by YSkakun on 2/28/2017.
 */
@Component
public class LoginSuccessEventHandler implements ApplicationListener<LoginSuccessEvent> {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(LoginSuccessEvent loginSuccessEvent) {
        Authentication authentication = (Authentication) loginSuccessEvent.getSource();
        System.out.println("Reset login failed attempts for user " + authentication.getPrincipal());
        resetLoginFailedAttempts(authentication);
    }

    private void resetLoginFailedAttempts(Authentication authentication) {
        User user = userService.findByUserName((String) authentication.getPrincipal());

        if (user != null) {
            user.setEnabled(true);
            user.setFailedLoginAttempts(0);
            userService.saveOrUpdate(user);
        }
    }
}
