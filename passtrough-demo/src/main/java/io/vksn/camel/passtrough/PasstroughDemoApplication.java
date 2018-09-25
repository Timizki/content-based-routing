package io.vksn.camel.passtrough;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class PasstroughDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasstroughDemoApplication.class, args);
	}
}
