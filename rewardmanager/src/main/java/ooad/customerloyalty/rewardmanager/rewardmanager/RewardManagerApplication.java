package ooad.customerloyalty.rewardmanager.rewardmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class RewardManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardManagerApplication.class, args);
	}

}
