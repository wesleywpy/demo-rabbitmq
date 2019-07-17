package com.wesley.growth.original.limit;

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
        Channel channel = ChannelUtils.getChannelInstance("消费端 流量限制");

        String exchangeName = "test_exchange_qos";
        String routingKey = "qos.save";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true, false, false, new HashMap<>());

        String msg = "这是一条测试消息 (验证流量限制)";
        for (int i = 0; i < 5; i++) {
            channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());
        }
    }
}
