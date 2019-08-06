package com.wesley.growth.tx.domain.dto;

import lombok.Data;

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
public class OrderInsertDto {

    private BigDecimal amount;

    private String customerName;
}
