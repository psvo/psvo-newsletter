package psvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Bootstrap class
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

    /**
     * Main application function. Used when deploying as standalone application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /* Used when deploying as WAR. */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
