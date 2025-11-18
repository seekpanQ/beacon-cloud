package com.mashibing.api.controller;

import com.mashibing.api.form.SingleSendForm;
import com.mashibing.api.utils.R;
import com.mashibing.api.vo.ResultVO;
import com.mashibing.common.enums.ExceptionEnums;
import lombok.extern.slf4j.Slf4j;
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
        //0. 声明真实IP地址
        String ip;
        //1. 基于x-forwarded-for请求头获取IP地址
        String ips = req.getHeader("x-forwarded-for");
        //  直接基于第一个,的位置，截取需要的IP地址
        if (!StringUtils.isEmpty(ips) && !UNKNOW.equalsIgnoreCase(ips)) {
            if (ips.contains(",")) {
                return ips.substring(0, ips.indexOf(","));
            } else {
                return ips;
            }
        }
        // 2. 基于请求头获取IP地址，基于request请求头获取信息时，除了null和空串外，还有可能拿到unknow，
        ip = req.getHeader("x-real-ip");
        if (StringUtils.isEmpty(ip) || UNKNOW.equalsIgnoreCase(ip)) {
            // x-real-ip没拿到，考虑一下其他的代理服务器
            //3.  Apache的服务器，请求头中携带真实IP的名称是 proxy-client-ip
            ip = req.getHeader("proxy-client-ip");
        }
        //4. 如果real没有拿到，判断apache是否拿到了。
        if (StringUtils.isEmpty(ip) || "unknow".equalsIgnoreCase(ip)) {
            // 5. 如果Apache服务器没拿到，考虑一手WebLogic, wl-proxy-client-ip
            ip = req.getHeader("wl-proxy-client-ip");
        }
        //6. 判断WebLogic有木有拿到IP
        if (StringUtils.isEmpty(ip) || "unknow".equalsIgnoreCase(ip)) {
            //7. 基于其他的代理服务器的方式获取请求头的IP地址
            ip = req.getHeader("http_client_ip");
        }
        //8. 如果上诉方式都获取不到，
        if (StringUtils.isEmpty(ip) || "unknow".equalsIgnoreCase(ip)) {
            // 9. 基于传统方式获取IP
            ip = req.getRemoteAddr();
        }
        //10. 返回
        return ip;
    }

}
