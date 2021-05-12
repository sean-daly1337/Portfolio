package com.sd4.AssignmentOne;

import java.util.Locale;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@SpringBootApplication
@ComponentScan({"service", "controller"})
@EntityScan("model")
@EnableJpaRepositories("repository")


public class AssignmentOneApplication implements CommandLineRunner, WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentOneApplication.class, args);
	}
        
        
        @Bean
        public LocaleResolver localeResolver() {
                
            SessionLocaleResolver slr = new SessionLocaleResolver();
            slr.setDefaultLocale(new Locale("en", "IE"));
            return slr;
            }


        @Bean
        public LocaleChangeInterceptor localeChangeInterceptor() {
                
                LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
                lci.setParamName("lang");
                return lci;
            }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            
                registry.addInterceptor(localeChangeInterceptor());
        }

             
    @Override
    public void run(String... args) throws Exception {
        
    }

}
