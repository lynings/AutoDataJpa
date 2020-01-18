package pers.lyning.autodatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author lyning
 */
@SpringBootApplication
@EnableJpaAuditing
public class AutoDataJpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutoDataJpaApplication.class, args);
    }
}
