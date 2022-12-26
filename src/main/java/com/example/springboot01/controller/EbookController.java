package com.example.springboot01.controller;

import com.example.springboot01.domain.Ebook;
import com.example.springboot01.service.EbookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @RequestMapping("/list")
    public List<Ebook> list(){
        return ebookService.list();
    }
}
