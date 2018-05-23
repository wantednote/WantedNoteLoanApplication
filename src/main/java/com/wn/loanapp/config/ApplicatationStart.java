package com.wn.loanapp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.wn.loanapp.*"} , exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
public class ApplicatationStart extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicatationStart.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApplicatationStart.class, args);
	}
	
	
}