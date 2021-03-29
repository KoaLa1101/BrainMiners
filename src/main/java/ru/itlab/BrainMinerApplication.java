package ru.itlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.itlab.config.RootConfig;
import ru.itlab.config.WebConfig;
import ru.itlab.config.WebSecurityConfig;
import ru.itlab.controllers.AdminController;
import ru.itlab.controllers.MyController;
import ru.itlab.controllers.SignController;

@Configuration
@SpringBootApplication
@Import({RootConfig.class, WebSecurityConfig.class, WebConfig.class})
public class BrainMinerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrainMinerApplication.class, args);
    }

}
