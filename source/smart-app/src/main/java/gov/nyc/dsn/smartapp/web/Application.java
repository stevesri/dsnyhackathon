package gov.nyc.dsn.smartapp.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("gov.nyc.dsn.smartapp")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
