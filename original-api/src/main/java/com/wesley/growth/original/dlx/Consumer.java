package com.wesley.growth.original.dlx;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.wesley.growth.original.CommonConsumer;
import com.wesley.growth.original.support.ChannelUtils;

import java.io.IOException;
import java.util.HashMap;

/**
 * <p>
 * 死信 队列演示
 * </p>
 * Email yani@uoko.com
 *
 * @author Created by Yani on 2019/09/02
 */
public class Consumer {

    public static void main(String[] args) throws IOException {
        Channel channel = ChannelUtils.getChannelInstance("死信队列 DLX");

        String exchangeName = "test_exchange_dlx";
        String queueName = "test_queue_dlx";
        String routingKey = "dlx.#";

        // autoDelete: 当最后一个绑定被删除后，该交换机将被删除
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true, false, null);

        // 当前队列出现 死信消息 情况, 消息 push到 指定名称的 exchange
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "dlx.exchange");
        channel.queueDeclare(queueName, true, false, false, arguments);
        channel.queueBind(queueName, exchangeName, routingKey);

        // 死信队列声明:
        String dlxQueueName = "dlx.queue";
        channel.exchangeDeclare("dlx.exchange", BuiltinExchangeType.TOPIC, true, false, null);
        channel.queueDeclare(dlxQueueName, true, false, false, null);
        channel.queueBind("dlx.queue", "dlx.exchange", "#");

//        channel.basicConsume(queueName, true, new CommonConsumer(channel));
        // 消费死信队列消息
        channel.basicConsume(dlxQueueName, true, new CommonConsumer(channel));
    }
}
