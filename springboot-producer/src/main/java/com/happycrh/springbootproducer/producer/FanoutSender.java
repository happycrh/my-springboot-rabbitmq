package com.happycrh.springbootproducer.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FanoutSender {
    //自动注入RabbitTemplate模板类
    @Autowired
    private RabbitTemplate rabbitTemplate;

    AmqpTemplate amqpTemplate;
    //回调函数: confirm确认
/*    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            Date orderTime = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
            String time = simpleDateFormat.format(orderTime);
            System.err.println("time: " + time);
            System.err.println("correlationData: " + correlationData);
            String messageId = correlationData.getId();
            if(ack){
                //如果confirm返回成功 则进行更新
            } else {
                //失败则进行具体的后续操作:重试 或者补偿等手段
                System.err.println("异常处理...");
            }
        }
    };*/

    //发送消息方法调用: 构建自定义对象消息
    public void sendOrder(String time) throws Exception {
        // 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
        //rabbitTemplate.setConfirmCallback(this::confirm);
        //消息唯一ID
        System.out.println("time:"+time);
        CorrelationData correlationData = new CorrelationData("order.getMessageId()");
        rabbitTemplate.convertAndSend("order-exchange-fanout", "", time, correlationData);
    }

}
