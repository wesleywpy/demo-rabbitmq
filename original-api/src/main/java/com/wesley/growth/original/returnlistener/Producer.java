package com.wesley.growth.original.returnlistener;

import com.rabbitmq.client.*;
import com.wesley.growth.original.support.ChannelUtils;

import java.io.IOException;
import java.util.HashMap;

/**
 * 消息生产者
 * Return消息机制
 * @author Created by Wesley on 2019/7/13.
 */
public class Producer {

    public static void main(String[] args) throws IOException {
        Channel channel = ChannelUtils.getChannelInstance("Return消息机制");

        String exchangeName = "test_exchange_return";
        String routingKey = "return.save";
        // 声明交换机 (交换机名, 交换机类型, 是否持久化, 是否自动删除, 是否是内部交换机, 交换机属性);
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, false, new HashMap<>());

        // deliveryMode 持久化模式 expiration:过期时间 单位毫秒
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties()
                .builder()
                .deliveryMode(2)
                .contentType("UTF-8")
                .expiration("10000")
                .build();

        // 生产者投递消息后，当前的Exchange不存在或路由key找不到，监听这种不可达的消息
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingkey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.err.println("---------handle  return----------");
                System.err.println("replyCode: " + replyCode);
                System.err.println("replyText: " + replyText);
                System.err.println("exchange: " + exchange);
                System.err.println("routingKey: " + routingkey);
                System.err.println("properties: " + properties);
                System.err.println("body: " + new String(body));
            }
        });

        // mandatory 参数必须设置为true, 才会启用
        String msg = "这是一条确认消息 (Return消息机制)";
        channel.basicPublish(exchangeName, routingKey, true, basicProperties, msg.getBytes());
    }
}
