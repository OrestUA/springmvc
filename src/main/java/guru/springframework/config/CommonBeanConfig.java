package guru.springframework.config;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by YSkakun on 11/3/2016.
 */
@Configuration
@EnableJpaRepositories("guru.springframework.repositories")
@EnableScheduling
public class CommonBeanConfig {

    @Bean
    public StrongPasswordEncryptor strongEncryptor() {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor;
    }
}
