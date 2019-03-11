package hm.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import hm.Application;

@SpringBootApplication(scanBasePackages = "hm")
//@EnableAutoConfiguration
//@ComponentScan(basePackages = "${spring.component-scan}")
public class AppExecutor {

	public static void run(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
