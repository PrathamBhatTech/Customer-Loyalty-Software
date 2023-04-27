package ooad.customerloyalty.rewardmanager.rewardmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ooad.customerloyalty.rewardmanager.rewardmanager")
public class RewardManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardManagerApplication.class, args);
	}

}
