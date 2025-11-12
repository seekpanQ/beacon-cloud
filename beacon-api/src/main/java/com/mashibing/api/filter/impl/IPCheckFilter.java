package com.mashibing.api.filter.impl;

import com.mashibing.api.filter.CheckFilter;
import com.mashibing.common.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 校验请求的ip地址是否是白名单
 */
@Slf4j
@Service(value = "ip")
public class IPCheckFilter implements CheckFilter {
    @Override
    public void check(StandardSubmit submit) {
        log.info("【接口模块-校验ip】   校验ing…………");
    }
}
