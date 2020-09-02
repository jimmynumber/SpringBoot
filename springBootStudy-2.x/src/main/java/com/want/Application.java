package com.want;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring4all.swagger.EnableSwagger2Doc;

@SpringBootApplication
@EnableSwagger2Doc
//@EnableOpenApi
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
