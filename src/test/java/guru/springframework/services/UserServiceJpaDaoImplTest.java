package guru.springframework.services;

import guru.springframework.domain.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by YSkakun on 11/3/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveOrUpdateUser() throws Exception {
        User user = new User();

        user.setUsername("somename");
        user.setPassword("pwd1234");

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getEncryptedPassword());

        System.out.println("Encrypted password:\n" + savedUser.getEncryptedPassword());
    }

}
