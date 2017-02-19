package guru.springframework.services.security;

import org.aspectj.lang.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Created by Fudjitsu on 19.02.2017.
 */
@Aspect
@Component
public class LoginAspect {

    @Pointcut("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))")
    public void doAuthenticate(){

    }

    @Before("guru.springframework.services.security.LoginAspect.doAuthenticate() && args(authentication)")
    public void logBefore(Authentication authentication){

        System.out.println("before authentication " + authentication.isAuthenticated());

    }

    @AfterReturning(value = "guru.springframework.services.security.LoginAspect.doAuthenticate()", returning = "authentication")
    public void logAfterAuthentication(Authentication authentication){

        System.out.println("after authentication " + authentication.isAuthenticated());

    }

    @AfterThrowing("guru.springframework.services.security.LoginAspect.doAuthenticate() && args(authentication)")
    public void logAuthenticationException(Authentication authentication){
        String userDetails = (String) authentication.getPrincipal();
        System.out.println("Login failed for user: " + userDetails);

    }

}