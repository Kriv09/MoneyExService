package ExchangeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class MoneyExServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyExServiceApplication.class, args);
    }

}
