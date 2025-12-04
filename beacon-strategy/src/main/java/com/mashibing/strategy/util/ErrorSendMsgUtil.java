package com.mashibing.strategy.util;


import com.mashibing.common.constant.RabbitMQConstants;
import com.mashibing.common.constant.SmsConstant;
import com.mashibing.common.model.StandardSubmit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 向MQ发送信息的Util
 */
@Component
public class ErrorSendMsgUtil {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendWriteLog(StandardSubmit submit) {
        submit.setReportState(SmsConstant.REPORT_FAIL);
        // 发送消息到写日志队列
        rabbitTemplate.convertAndSend(RabbitMQConstants.SMS_WRITE_LOG, submit);

    }
}
