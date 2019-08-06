package com.wesley.growth.tx.service.impl;

import com.wesley.growth.tx.domain.dto.OrderInsertDto;
import com.wesley.growth.tx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 * Email yani@uoko.com
 *
 * @author Created by Yani on 2019/08/06
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void add(OrderInsertDto insertDto) {
        // 业务操作, 写入订单数据

        // 1. 消息入库, 初始化消息的状态, 并设置 确认消息的超时时间
        // 2. 发送消息
        rabbitTemplate.setMandatory(true);


    }

}
