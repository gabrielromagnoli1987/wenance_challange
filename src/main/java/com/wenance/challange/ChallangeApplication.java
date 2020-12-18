package com.wenance.challange;

import com.wenance.challange.tasks.LastPriceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChallangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallangeApplication.class, args);
	}

}
