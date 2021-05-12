package com.sd4.AssignmentOne;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"service", "controller"})
@EntityScan("model")
@EnableJpaRepositories("repository")
//@EnableVaadin({"com.sd4.vaadin.views"})


public class AssignmentOneApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentOneApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        
    }

}
