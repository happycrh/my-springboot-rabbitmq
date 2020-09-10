package com.happycrh.springbootproducer.controller;

import com.happycrh.springbootproducer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenrenhui
 * @version 0.0.1
 * @date 2020/9/7 15:57
 * @desc
 */
@RestController
@RequestMapping
public class TController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/test1")
    public String t() throws Exception {
        for (int i = 0; i < 20; i++) {
            orderService.createOrderTopic(i);
        }
        return "ok";
    }
    @GetMapping("/test2")
    public String t2() throws Exception {
        for (int i = 0; i < 5; i++) {
            orderService.createOrderFanout(i);
        }
        return "ok";
    }
    @GetMapping("/test3")
    public String t3() throws Exception {
        for (int i = 0; i < 10; i++) {
            orderService.createOrderWorker(i);
        }
        return "ok";
    }


}
