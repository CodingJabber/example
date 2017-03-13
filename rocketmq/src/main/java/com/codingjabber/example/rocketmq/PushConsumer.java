package com.codingjabber.example.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by 蟹老板 on 2017/3/10.
 */
public class PushConsumer {
    public static void main(String args[]) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("TestProducerGroup"); //订阅模式
        //DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("TestProducerGroup"); //pull模式
        consumer.setNamesrvAddr("192.168.137.101:9876"); //name server address
        consumer.subscribe("TestTopic", "TestTag");
        consumer.subscribe("TestTopic1", "TagA||TagC||TagD");
        consumer.subscribe("TestTopic2", "*");
        //consumer.setMessageModel(MessageModel.BROADCASTING); //集群消费，广播消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET); //默认值CONSUME_FROM_LAST_OFFSET
        consumer.setConsumeMessageBatchMaxSize(1);
        consumer.setVipChannelEnabled(false);
        consumer.setConsumeThreadMax(100);
        consumer.setConsumeThreadMin(10);
        consumer.registerMessageListener((List<MessageExt> list, ConsumeConcurrentlyContext context) -> {
            Message msg = list.get(0);
            System.out.printf("Topic:%s, Tags:%s, Body:%s%n",msg.getTopic(),msg.getTags(),new String(msg.getBody()));
            if("TagA".equals(msg.getTags())){
                System.out.println("TagA.");
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        System.out.println("consumer start success.");
    }
}
