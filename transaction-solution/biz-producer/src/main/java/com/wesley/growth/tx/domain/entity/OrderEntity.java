package com.wesley.growth.tx.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 * Email yani@uoko.com
 *
 * @author Created by Yani on 2019/08/06
 */
@Data
public class OrderEntity implements Serializable {

    private Long id;

    private String orderNum;

    private BigDecimal amount;

    private String customerName;
}
