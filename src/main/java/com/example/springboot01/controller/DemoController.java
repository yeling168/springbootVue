package com.example.springboot01.controller;

import com.example.springboot01.domain.Demo;
import com.example.springboot01.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @RequestMapping("/list")
    public List<Demo> list(){
        return demoService.list();
    }
}
