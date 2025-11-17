package com.mashibing.api.filter;

import com.mashibing.common.model.StandardSubmit;

/**
 * 做策略模式的父接口
 */
public interface CheckFilter {

    /**
     * 校验
     *
     * @param submit
     */
    void check(StandardSubmit submit);
}
