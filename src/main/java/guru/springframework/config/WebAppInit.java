package guru.springframework.config;

import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Fudjitsu on 08.03.2017.
 */
public class WebAppInit implements WebApplicationInitializer {


    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {

        servletContext.setInitParameter("spring.profiles.active", "springdatajpa");

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfig.class);

        //dispatcher servlet
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        //H2 console
        String path = "/h2-console";
        String urlMapping = (path.endsWith("/") ? path + "*" : path + "/*");
        ServletRegistration.Dynamic h2Console = servletContext.addServlet("h2", new WebServlet());
        h2Console.setLoadOnStartup(1);
        h2Console.addMapping(urlMapping);

    }
}
