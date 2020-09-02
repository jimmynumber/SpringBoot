package com.want.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestController ==@Controller+@ResponseBody
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String index() {
        return "Hello World";
    }
    
    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }
 

}