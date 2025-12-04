package com.mashibing.strategy.filter.impl;

import com.mashibing.common.enums.ExceptionEnums;
import com.mashibing.common.exception.StrategyException;
import com.mashibing.common.model.StandardSubmit;
import com.mashibing.strategy.filter.StrategyFilter;
import com.mashibing.strategy.util.DFAUtil;
import com.mashibing.strategy.util.ErrorSendMsgUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service(value = "dfaDirtyWord")
@Slf4j
public class DirtyWordDFAStrategyFilter implements StrategyFilter {
    @Autowired
    private ErrorSendMsgUtil errorSendMsgUtil;
    @Override
    public void strategy(StandardSubmit submit) {
        log.info("【策略模块-敏感词校验】   校验ing…………");
        //1、 获取短信内容
        String text = submit.getText();
        //2、 调用DFA查看敏感词
        Set<String> dirtyWords = DFAUtil.getDirtyWord(text);
        //4、 根据返回的set集合，判断是否包含敏感词
        if (dirtyWords != null && dirtyWords.size() > 0) {
            //5、 如果有敏感词，抛出异常 / 其他操作。。
            log.info("【策略模块-敏感词校验】   短信内容包含敏感词信息， dirtyWords = {}", dirtyWords);
            // ================================发送写日志================================
            submit.setErrorMsg(ExceptionEnums.HAVE_DIRTY_WORD.getMsg() + "dirtyWords = " + dirtyWords.toString());
            errorSendMsgUtil.sendWriteLog(submit);
            // ================================抛出异常================================
            throw new StrategyException(ExceptionEnums.HAVE_DIRTY_WORD);
        }
    }
}
