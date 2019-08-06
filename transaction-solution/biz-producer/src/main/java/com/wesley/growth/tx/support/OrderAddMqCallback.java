package com.wesley.growth.tx.support;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * <p>
 * RabbitMq Confirm和Return机制 确保消息投递成功
 * </p>
 * Email yani@uoko.com
 *
 * @author Created by Yani on 2019/08/06
 */
public class OrderAddMqCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // step3: 确认消息
        // step4: 如果消息确认成功, 则修改消息在数据库中的状态

        // step5: 确认失败, 等待一定时间重新发送到队列
        // 消息重试次数超过一定限制, 将消息修改为投递失败的状态, 后续通过其他机制进行手动处理.
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        // 生产者投递消息后，当前的Exchange不存在或路由key找不到，监听这种不可达的消息

        // step5: 等待一定时间重新发送到队列
        // 消息重试次数超过一定限制, 将消息修改为投递失败的状态, 后续通过其他机制进行手动处理.
    }

}
