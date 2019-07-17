package com.wesley.growth.original.limit;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.wesley.growth.original.support.ChannelUtils;

import java.io.IOException;
import java.util.HashMap;

/**
 * 消费端 流量限制
 * @author Created by Wesley on 2019/07/17
 */
public class Consumer {

    public static void main(String[] args) throws IOException {
        Channel channel = ChannelUtils.getChannelInstance("消费端 流量限制");

        String exchangeName = "test_exchange_qos";
        String queueName = "test_queue_qos";
        String routingKey = "qos.#";

        // 声明交换机 (交换机名, 交换机类型, 是否持久化, 是否自动删除, 是否是内部交换机, 交换机属性);
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true, false, false, new HashMap<>());

        // 声明队列 (队列名, 是否持久化, 是否排他, 是否自动删除, 队列属性);
        channel.queueDeclare(queueName, true, false, false, new HashMap<>());

        // 将队列Binding到交换机上 (队列名, 交换机名, Routing key, 绑定属性);
        channel.queueBind(queueName, exchangeName, routingKey);

        LimitConsumer limitConsumer = new LimitConsumer(channel);

        // 消息限流(单条消息大小, 一次推送的消息数量, 是否用于channel级别)
        // 0 不限制 1 一次推送一条 false 应用于consumer级别
        channel.basicQos(0, 1, false);
        // 限流必须把 自动ack设置为false
        channel.basicConsume(queueName, false, limitConsumer);
    }

}
