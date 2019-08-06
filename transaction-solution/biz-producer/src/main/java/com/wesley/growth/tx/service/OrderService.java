package com.wesley.growth.tx.service;

import com.wesley.growth.tx.domain.dto.OrderInsertDto;

/**
 * <p>
 *
 * </p>
 * Email yani@uoko.com
 *
 * @author Created by Yani on 2019/08/06
 */
public interface OrderService {

    /**
     * 新增订单
     */
    void add(OrderInsertDto insertDto);
}
