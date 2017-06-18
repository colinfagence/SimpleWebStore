package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"controllers","db"})
@EntityScan(basePackages = {"db.model"})
public class main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run( main.class, args );
	}

}
