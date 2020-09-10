package com.happycrh.springbootproducer;

import com.happycrh.springbootproducer.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootProducerApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
   public void contextLoads() throws Exception {
        orderService.createOrderWorker(1);
    }

}
