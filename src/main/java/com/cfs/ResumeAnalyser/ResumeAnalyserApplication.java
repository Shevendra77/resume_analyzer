package com.cfs.ResumeAnalyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(basePackages = "com.cfs.ResumeAnalyser.repository")
public class ResumeAnalyserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeAnalyserApplication.class, args);
	}

}