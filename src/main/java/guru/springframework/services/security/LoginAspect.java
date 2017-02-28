package guru.springframework.services.security;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 1/6/16.
 */
@Aspect
@Component
public class LoginAspect {

    private UserDetailsService userDetailsService;

    private LoginFailureEventPublisher publisher;

    private LoginSuccessEventPublisher loginSuccessEventPublisher;

    private UserService userService;

    private EncryptionServiceImpl encryptionService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    public void setPublisher(LoginFailureEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Autowired
    public void setLoginSuccessEventPublisher(LoginSuccessEventPublisher loginSuccessEventPublisher) {
        this.loginSuccessEventPublisher = loginSuccessEventPublisher;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Qualifier(value = "userDetailsService")
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setEncryptionService(EncryptionServiceImpl encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Pointcut("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))")
    public void doAuthenticate() {

    }

    @Before("guru.springframework.services.security.LoginAspect.doAuthenticate() && args(authentication)")
    public void logBefore(Authentication authentication) {
        System.out.println("This is before the Authenticate Method: authentication: " + authentication.isAuthenticated());
    }

//    @After("guru.springframework.services.security.LoginAspect.doAuthenticate() && args(authentication)")
//    public void logAfter(Authentication authentication) {
//        System.out.println("This is after the Authenticate Method: authentication: " + authentication.isAuthenticated());
//        if (unblockUser(authentication)) {
//            System.out.println("Reset failed attempts to 0 if user entered valid creds");
//            loginSuccessEventPublisher.publish(new LoginSuccessEvent(authentication));
//        }
//    }


    @AfterReturning(value = "guru.springframework.services.security.LoginAspect.doAuthenticate()", returning = "authentication")
    public void logAfterAuthenticate(Authentication authentication) {
        System.out.println("This is after the Authenticate Method authentication: " + authentication.isAuthenticated());
    }

    @AfterThrowing("guru.springframework.services.security.LoginAspect.doAuthenticate() && args(authentication)")
    public void logAuthenicationException(Authentication authentication) {
        String userDetails = (String) authentication.getPrincipal();
        System.out.println("Login failed for user: " + userDetails);

        publisher.publish(new LoginFailureEvent(authentication));
        if (unblockUser(authentication)) {
            System.out.println("Reset failed attempts to 0 if user entered valid creds");
            loginSuccessEventPublisher.publish(new LoginSuccessEvent(authentication));
            authenticationProvider.authenticate(authentication);
        }
    }

    private boolean unblockUser(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        boolean unblock = false;

        if (userDetails != null && !userDetails.isEnabled()) {
            String encryptedPass = userDetails.getPassword();
            unblock = encryptionService.checkPassword(password, encryptedPass);
        }
        return unblock;
    }
}