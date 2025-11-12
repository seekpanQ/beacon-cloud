package com.mashibing.api.filter.impl;

import com.mashibing.api.filter.CheckFilter;
import com.mashibing.common.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 校验客户的apikey是否合法
 */
@Service(value = "apikey")
@Slf4j
public class ApiKeyCheckFilter implements CheckFilter {
    @Override
    public void check(StandardSubmit submit) {
        log.info("【接口模块-校验apikey】   校验ing…………");
    }
}
