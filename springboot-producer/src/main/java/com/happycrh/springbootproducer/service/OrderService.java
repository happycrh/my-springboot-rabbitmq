package com.happycrh.springbootproducer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.happycrh.springbootproducer.producer.FanoutSender;
import com.happycrh.springbootproducer.producer.TopicSender;
import com.happycrh.springbootproducer.producer.WokerSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private TopicSender topicSender;
    @Autowired
    private FanoutSender fanoutSender;
    @Autowired
    private WokerSender wokerSender;

    public void createOrderTopic(int i) throws Exception {
        // 使用当前时间当做订单创建时间（为了模拟一下简化）
        Date orderTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        String time = simpleDateFormat.format(orderTime);
        // 插入业务数据
        // 发送消息
        topicSender.sendOrder(time+"===="+i);
    }
    public void createOrderFanout(int i) throws Exception {
        // 使用当前时间当做订单创建时间（为了模拟一下简化）
        Date orderTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        String time = simpleDateFormat.format(orderTime);
        // 插入业务数据
        // 发送消息
        fanoutSender.sendOrder(time+"===="+i);
    }

    public void createOrderWorker(int i) throws Exception {
        // 使用当前时间当做订单创建时间（为了模拟一下简化）
        Date orderTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        String time = simpleDateFormat.format(orderTime);
        // 插入业务数据
        // 发送消息
        wokerSender.sendOrder(time+"===="+i);
    }

    public void createOrderWorkerJSONObject(int i) throws Exception {
        // 使用当前时间当做订单创建时间（为了模拟一下简化）
        Date orderTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        String time = simpleDateFormat.format(orderTime);
        // 插入业务数据
        // 发送消息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("receivableId","123123213213");
        jsonObject.put("receivedMoney",555);
        wokerSender.sendOrderJSONObject(jsonObject);
    }

}

