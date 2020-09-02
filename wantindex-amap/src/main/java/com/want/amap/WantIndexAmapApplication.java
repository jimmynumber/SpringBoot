package com.want.amap;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WantIndexAmapApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(WantIndexAmapApplication.class).bannerMode(Banner.Mode.OFF).web(false).run(args);
	}
}
