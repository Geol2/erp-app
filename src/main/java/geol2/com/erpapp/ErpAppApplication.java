package geol2.com.erpapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ErpAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(ErpAppApplication.class, args);
	}
}
