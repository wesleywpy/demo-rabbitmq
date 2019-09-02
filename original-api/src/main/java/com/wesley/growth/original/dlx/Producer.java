package com.wesley.growth.original.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.wesley.growth.original.support.ChannelUtils;

import java.io.IOException;
import java.util.HashMap;

/**
 * 消息生产者
 * @author Created by Wesley on 2019/7/13.
 */
public class Producer {

    public static void main(String[] args) throws IOException {
        Channel channel = ChannelUtils.getChannelInstance("死信队列 DLX");

        String exchangeName = "test_exchange_dlx";
        String routingKey = "dlx.save";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true, false, false, new HashMap<>());

        String msg = "这是一条 DLX 测试消息";
        for (int i = 0; i < 5; i++) {
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                    .deliveryMode(2)
                    .contentEncoding("UTF-8")
                    .expiration("10000")
                    .build();
            channel.basicPublish(exchangeName, routingKey, properties, msg.getBytes());
        }
    }
}
