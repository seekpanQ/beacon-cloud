package com.mashibing.api.controller;

import com.mashibing.api.form.SingleSendForm;
import com.mashibing.api.utils.R;
import com.mashibing.api.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sms")
public class SmsController {

    @RequestMapping(value = "single_send", produces = "application/json;charset=utf-8")
    public ResultVO singleSend(@RequestBody SingleSendForm singleSendForm) {
        return R.ok();
    }

}
