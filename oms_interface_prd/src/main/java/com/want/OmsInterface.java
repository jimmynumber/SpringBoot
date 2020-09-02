package com.want;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class OmsInterface {

	public OmsInterface() {
		super();
	}

	public static void main(String[] args) {
		SpringApplication.run(OmsInterface.class, args);
	}

}
