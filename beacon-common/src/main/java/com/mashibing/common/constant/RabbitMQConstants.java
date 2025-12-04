package com.mashibing.common.constant;

/**
 * RabbitMQ中的一些队列信息
 */
public interface RabbitMQConstants {
    /**
     * 接口模块发送消息到策略模块的队列名称
     */
    String SMS_PRE_SEND = "sms_pre_send_topic";
    /**
     * 策略模块发送手机号归属地&运营商到后台管理模块的队列名称
     */
    String MOBILE_AREA_OPERATOR = "mobile_area_operator_topic";

    /**
     * 写日志到Elasticsearch的队列
     */
    String SMS_WRITE_LOG = "sms_write_log_topic";
}
