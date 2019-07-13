package com.wesley.growth.original.confirm;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.wesley.growth.original.support.ChannelUtils;

import java.io.IOException;
import java.util.HashMap;

/**
 * 消息生产者
 * 验证Confirm机制
 * @author Created by Wesley on 2019/7/13.
 */
public class Producer {

    public static void main(String[] args) throws IOException {
        Channel channel = ChannelUtils.getChannelInstance("验证Confirm机制");

        String exchangeName = "test_exchange_confirm";
        String routingKey = "confirm.save";
        // 声明交换机 (交换机名, 交换机类型, 是否持久化, 是否自动删除, 是否是内部交换机, 交换机属性);
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, false, new HashMap<>());

        // 消息投递模式: 消息确认模式
        channel.confirmSelect();

        // deliveryMode 持久化模式 expiration:过期时间 单位毫秒
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties()
                .builder()
                .deliveryMode(2)
                .contentType("UTF-8")
                .expiration("10000")
                .build();

        String msg = "这是一条确认消息 (验证Confirm机制)";
        // 设置消息属性 发布消息 (交换机名, Routing key, 可靠消息相关属性 后续会介绍, 消息属性, 消息体);
        channel.basicPublish(exchangeName, routingKey, basicProperties, msg.getBytes());

        // 确认消息监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("-------- ack! --------");
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("-------- no ack! --------");
            }
        });
    }
}
