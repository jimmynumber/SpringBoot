package com.want;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.want.controller.HelloController;



@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = MockServletContext.class)
@SpringBootTest(classes = SpringBootStudyApplication.class)
@ContextConfiguration
@WebAppConfiguration
public class HelloControllerTest {

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		System.out.println("11111111111111111111");
		mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
		//mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getHello() throws Exception {
		System.out.println("111111111111111111112222");
//		mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());
//				//.andExpect(content().string(equalTo("Hello World")));
		 MvcResult authResult = null;
	        authResult = mvc.perform(MockMvcRequestBuilders.get("/hello")//使用get方式来调用接口。
	                .contentType(MediaType.APPLICATION_JSON)//请求参数的类型
	                //.param("sessionid", "ZlbpLxXw")//请求的参数（可多个）
	        ).andExpect(status().isOk())
	         .andReturn();
	        //获取数据
	        JSONObject jsonObject =new  JSONObject(authResult.getResponse().getContentAsString());
	        JSONArray jsonArrayData = (JSONArray)jsonObject.get("data");
	      //获取第一个Array中的值,判断查询到的结果。
	        JSONObject jsonObject_data = null;
	        if(jsonArrayData.length()>0){
	            jsonObject_data = (JSONObject) jsonArrayData.get(0);
	        }
	        //加断言，判断属性值的问题。
	        Assert.assertNotNull(jsonObject.get("error_code"));
	        Assert.assertEquals(jsonObject.get("error_code"),0);
	        Assert.assertNotNull(jsonObject.get("error_msg"));
	        Assert.assertEquals(jsonObject.get("error_msg"),"操作成功");
	        Assert.assertNotNull(jsonObject.get("data"));
	        Assert.assertNotNull(jsonObject_data);
	        Assert.assertEquals(jsonObject_data.get("equipmentty"),1);
	        Assert.assertEquals(jsonObject_data.get("equipmenttypename"),"xxxxx");
	        
		System.out.println("1111111111111111111133333"+content());
	}

}

