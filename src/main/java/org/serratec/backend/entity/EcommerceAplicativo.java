package org.serratec.backend.entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EcommerceAplicativo {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceAplicativo.class, args);
    }
}
