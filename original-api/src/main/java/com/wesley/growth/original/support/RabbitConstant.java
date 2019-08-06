package com.wesley.growth.original.support;

/**
 * @author Created by Wesley on 2018/7/23.
 */
public abstract class RabbitConstant {

    private RabbitConstant() {

    }

    public static final String HOST = "192.168.200.42";

    public static final int PORT = 5672;

    public static final String VIRTUAL_HOST = "/";

    public static final String USERNAME = "guest";

    public static final String PASSWORD = "guest123";

    public static final String EXCHANGE_DIRECT_ORDER = "wesley.order";

    public static final String ROUTING_KEY_ADD = "add";

    public static final String QUEUE_ORDER_ADD = "wesley.order.add";

}
