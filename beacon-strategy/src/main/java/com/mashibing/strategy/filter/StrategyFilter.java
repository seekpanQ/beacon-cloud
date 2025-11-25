package com.mashibing.strategy.filter;

import com.mashibing.common.model.StandardSubmit;

/**
 * 做策略模式的父接口
 */
public interface StrategyFilter {

    /**
     * 校验
     *
     * @param submit
     */
    void strategy(StandardSubmit submit);
}
