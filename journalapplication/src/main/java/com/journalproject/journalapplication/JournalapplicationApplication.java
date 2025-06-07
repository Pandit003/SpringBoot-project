package com.journalproject.journalapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class JournalapplicationApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JournalapplicationApplication.class, args);
//		ConfigurableEnvironment environment = context.getEnvironment(); // this help know know which active profile or environment is set development or production environment
//		System.out.println(environment.getActiveProfiles()[0]);
	}
	
	@Bean
	public PlatformTransactionManager anyname(MongoDatabaseFactory dbfactory){
		return new MongoTransactionManager(dbfactory);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
