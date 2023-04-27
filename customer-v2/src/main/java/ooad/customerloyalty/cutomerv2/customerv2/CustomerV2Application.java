package ooad.customerloyalty.cutomerv2.customerv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class CustomerV2Application {

	public static void main(String[] args) {
		SpringApplication.run(CustomerV2Application.class, args);
	}

}
