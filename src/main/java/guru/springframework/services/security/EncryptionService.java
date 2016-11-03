package guru.springframework.services.security;

/**
 * Created by YSkakun on 11/3/2016.
 */
public interface EncryptionService {
    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}
