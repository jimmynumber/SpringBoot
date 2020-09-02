package com.want;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.want.service.BlogProperties;


/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStudyApplication.class)
public class BlogPropertiesTest {

	private static final Log log = LogFactory.getLog(SpringBootStudyApplication.class);

	@Autowired
	private BlogProperties blogProperties;


	@Test
	public void test1() throws Exception {
		/*
		//Assert.assertEquals();及其重载方法: 
		// 1. 如果两者一致, 程序继续往下运行. 
		// 2. 如果两者不一致, 中断测试方法, 抛出异常信息
		Assert.assertEquals("程序猿DD", blogProperties.getName());
		Assert.assertEquals("Spring Boot教程", blogProperties.getTitle());
		Assert.assertEquals("程序猿DD正在努力写《Spring Boot教程》", blogProperties.getDesc());
        */
		log.info("随机数测试输出：");
		log.info("随机字符串 : " + blogProperties.getValue());
		log.info("随机int : " + blogProperties.getNumber());
		log.info("随机long : " + blogProperties.getBignumber());
		log.info("随机10以下 : " + blogProperties.getTest1());
		log.info("随机10-20 : " + blogProperties.getTest2());

	}

}
