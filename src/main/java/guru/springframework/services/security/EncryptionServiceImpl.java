package guru.springframework.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by YSkakun on 11/3/2016.
 */
@Service
@Profile("jpadao")
public class EncryptionServiceImpl implements EncryptionService {

    @Autowired
    private StrongPasswordEncryptor strongEncryptor;

    @Override
    public String encryptString(String input) {
        return strongEncryptor.encryptPassword(input);
    }

    @Override
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return strongEncryptor.checkPassword(plainPassword,encryptedPassword);
    }
}
