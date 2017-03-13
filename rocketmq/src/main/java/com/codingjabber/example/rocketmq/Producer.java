package com.codingjabber.example.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * Created by 蟹老板 on 2017/3/10.
 */
public class Producer {
    public static void main(String args[]) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("TestProducerGroup");
        producer.setNamesrvAddr("192.168.137.101:9876");
        producer.start(); //只启动一次
        Message msg = new Message("TestTopic", "TestTag", "TestKeys", "This is body.".getBytes());
        SendResult res = producer.send(msg);
        System.out.println("send message success, id:" + res.getMsgId() + " result:" + res.getSendStatus());
    }
}
