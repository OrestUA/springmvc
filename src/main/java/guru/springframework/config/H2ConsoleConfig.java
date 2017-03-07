package guru.springframework.config;

import org.apache.catalina.servlets.WebdavServlet;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by YSkakun on 3/6/2017.
 */
@Configuration
public class H2ConsoleConfig {

    @Bean
    public ServletRegistrationBean h2Console() {
        String path = "/h2-console";
        String urlMapping = (path.endsWith("/") ? path + "*" : path + "/*");
        return new ServletRegistrationBean(new WebServlet(), urlMapping);
    }
}
