package guru.springframework.msscbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = ArtemisAutoConfiguration.class)
public class MsscBeerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsscBeerServiceApplication.class, args);
    }

}
