package com.mashibing.api.filter.impl;

import com.mashibing.api.filter.CheckFilter;
import com.mashibing.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 校验手机号的格式合法性
 */
@Service(value = "mobile")
@Slf4j
public class MobileCheckFilter implements CheckFilter {
    @Override
    public void check(StandardSubmit submit) {
        log.info("【接口模块-校验手机号】   校验ing…………");
    }
}
