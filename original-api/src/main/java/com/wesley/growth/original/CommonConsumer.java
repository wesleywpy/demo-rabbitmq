package com.wesley.growth.original;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 * Email yani@uoko.com
 *
 * @author Created by Yani on 2019/09/02
 */
public class CommonConsumer extends DefaultConsumer {

    public CommonConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("-------- consumerTag: " + consumerTag);
        System.out.println(envelope.toString());
        System.out.println(properties.toString());

        properties.getHeaders().forEach( (key, val) -> {
            System.out.println("-------- 自定义 header key: "+ key + ", value: "+ val);
        });
        System.out.println("消息内容:" + new String(body));
    }

}
