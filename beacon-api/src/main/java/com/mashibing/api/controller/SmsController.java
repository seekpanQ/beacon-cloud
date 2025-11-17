package com.mashibing.api.controller;

import com.mashibing.api.form.SingleSendForm;
import com.mashibing.api.utils.R;
import com.mashibing.api.vo.ResultVO;
import com.mashibing.common.enums.ExceptionEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sms")
@Slf4j
public class SmsController {

    @RequestMapping(value = "single_send", produces = "application/json;charset=utf-8")
    public ResultVO singleSend(@RequestBody @Validated SingleSendForm singleSendForm, BindingResult bindingResult) {
        //1. 校验参数
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getFieldError().getDefaultMessage();
            log.info("【接口模块-单条短信Controller】 参数不合法 msg = {}", msg);
            return R.error(ExceptionEnums.PARAMETER_ERROR.getCode(), msg);
        }
        return R.ok();
    }

}
