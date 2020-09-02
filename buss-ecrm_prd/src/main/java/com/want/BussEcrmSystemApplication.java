package com.want;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BussEcrmSystemApplication {

	public BussEcrmSystemApplication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		SpringApplication.run(BussEcrmSystemApplication.class, args);
	}

}
