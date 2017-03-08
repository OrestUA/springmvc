package guru.springframework.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Fudjitsu on 08.03.2017.
 */
@Configuration
@ComponentScan(basePackages = "guru.springframework")
@EnableWebMvc
public class SpringConfig extends WebMvcConfigurerAdapter {
}
