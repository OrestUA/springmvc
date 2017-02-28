package guru.springframework.services.security;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Fudjitsu on 28.02.2017.
 */
@Service
public class LoginServiceImpl implements LoginService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(fixedRate = 60_000)
    @Override
    public void resetFailedLogins() {
        System.out.println("Checking for locked accounts");

        List<User> userList = (List<User>) userService.listAll();

        userList.forEach(user -> {
            if (!user.getEnabled() && user.getFailedLoginAttempts() > 0) {
                System.out.println("Resetting failed attempts for user: " + user.getUsername());
                user.setFailedLoginAttempts(0);
                user.setEnabled(true);
                userService.saveOrUpdate(user);
            }
        });
    }
}