package com.mashibing.api.controller;

import com.mashibing.api.form.SingleSendForm;
import com.mashibing.api.utils.R;
import com.mashibing.api.vo.ResultVO;
import com.mashibing.common.enums.ExceptionEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/sms")
@Slf4j
public class SmsController {
    /**
     * 基于请求头获取信息时，可能获取到的未知信息
     */
    private final String UNKNOW = "unknow";
    /**
     * 如果是当前请求头获取IP地址，需要截取到第一个','未知
     */
    private final String X_FORWARDED_FOR = "x-forwarded-for";
    /**
     * 客户端IP地址的请求头信息，多个用','隔开。
     */
    @Value("${headers}")
    private String headers;

    /**
     * @param singleSendForm
     * @param bindingResult
     * @param req
     * @return
     */
    @RequestMapping(value = "single_send", produces = "application/json;charset=utf-8")
    public ResultVO singleSend(@RequestBody @Validated SingleSendForm singleSendForm,
                               BindingResult bindingResult, HttpServletRequest req) {
        //1. 校验参数
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getFieldError().getDefaultMessage();
            log.info("【接口模块-单条短信Controller】 参数不合法 msg = {}", msg);
            return R.error(ExceptionEnums.PARAMETER_ERROR.getCode(), msg);
        }
        //=========================获取真实的IP地址=========================================
        String ip = this.getRealIP(req);
        System.out.println(ip);
        return R.ok();
    }

    /**
     * 获取客户端真实的IP地址
     *
     * @param req
     * @return
     */
    private String getRealIP(HttpServletRequest req) {
        //1. 声明真实IP地址
        String ip;
        //2. 遍历请求头，并且通过req获取ip地址
        for (String header : headers.split(",")) {
            // 健壮性校验
            if (!StringUtils.isEmpty(header)) {
                // 基于req获取ip地址
                ip = req.getHeader(header);
                // 如果获取到的ip不为null，不为空串，并且不为unknow，就可以返回
                if (!StringUtils.isEmpty(ip) && !UNKNOW.equalsIgnoreCase(ip)) {
                    // 判断请求头是否是x-forwarded-for
                    if (X_FORWARDED_FOR.equalsIgnoreCase(header) && ip.indexOf(",") > 0) {
                        ip = ip.substring(0, ip.indexOf(","));
                    }
                    // 返回IP地址
                    return ip;
                }
            }
        }

        //3. 如果请求头都没有获取到IP地址，直接基于传统的方式获取一个IP
        return req.getRemoteAddr();
    }

}
