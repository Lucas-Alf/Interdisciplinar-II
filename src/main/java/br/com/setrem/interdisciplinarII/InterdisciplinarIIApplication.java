package br.com.setrem.interdisciplinarII;

import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterdisciplinarIIApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterdisciplinarIIApplication.class, args);
    }
}
