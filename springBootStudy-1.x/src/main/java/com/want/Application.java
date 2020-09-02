package com.want;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.statemachine.StateMachine;

import com.spring4all.mongodb.EnableMongoPlus;
import com.want.pojo.FooProperties;
import com.want.statemachine.Events;
import com.want.statemachine.States;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableMongoPlus
@EnableScheduling
@EnableAsync
@ComponentScan("com.want")
public class Application  implements CommandLineRunner {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		
		Binder binder = Binder.get(context.getEnvironment());

		// 绑定简单配置
		FooProperties foo = binder.bind("com.didispace", Bindable.of(FooProperties.class)).get();
		System.out.println(foo.getFoo());

		// 绑定List配置
		List<String> post = binder.bind("com.didispace.post", Bindable.listOf(String.class)).get();
		System.out.println(post);

//		List<PostInfo> posts = binder.bind("com.didispace.posts", Bindable.listOf(PostInfo.class)).get();
//		System.out.println(posts);

		// 读取配置
		System.out.println(context.getEnvironment().containsProperty("com.didispace.database-platform"));
		System.out.println(context.getEnvironment().containsProperty("com.didispace.databasePlatform"));
	
	}
	@Bean
	public DataLoader dataLoader() {
		return new DataLoader();
	}

	@Slf4j
	static class DataLoader implements CommandLineRunner {

		@Override
		public void run(String... strings) throws Exception {
			System.out.println("....33333.....Loading data...");
		}
	}
	
	@Autowired
	private StateMachine<States, Events> stateMachine;

	@Override
	public void run(String... args) throws Exception {
		stateMachine.start();
		stateMachine.sendEvent(Events.PAY);
		stateMachine.sendEvent(Events.RECEIVE);
	}
	
}
