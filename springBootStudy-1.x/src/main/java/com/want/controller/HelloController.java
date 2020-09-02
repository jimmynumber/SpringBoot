package com.want.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.want.exception.MyException;

import springfox.documentation.annotations.ApiIgnore;

//@RestController ==@Controller+@ResponseBody
@Controller
@ApiIgnore
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String index() {
        return "Hello World";
    }
    
    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }
 
    @RequestMapping("/test")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }
}