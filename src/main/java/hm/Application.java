package hm;

import java.net.MalformedURLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import hm.core.AppExecutor;

public class Application extends AppExecutor {

	public static void main(String[] args) throws MalformedURLException {
		run(args);
	}

}
