package com.mashibing.api.filter.impl;

import com.mashibing.api.filter.CheckFilter;
import com.mashibing.common.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 校验短信的模板
 */
@Service(value = "template")
@Slf4j
public class TemplateCheckFilter implements CheckFilter {
    @Override
    public void check(StandardSubmit submit) {
        log.info("【接口模块-校验模板】   校验ing…………");
    }
}
