package com.mashibing.strategy.filter.impl;

import com.mashibing.common.constant.CacheConstant;
import com.mashibing.common.enums.ExceptionEnums;
import com.mashibing.common.exception.StrategyException;
import com.mashibing.common.model.StandardSubmit;
import com.mashibing.strategy.client.BeaconCacheClient;
import com.mashibing.strategy.filter.StrategyFilter;
import com.mashibing.strategy.util.ErrorSendMsgUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * 敏感词校验
 */
@Service(value = "dirtyword")
@Slf4j
public class DirtyWordStrategyFilter implements StrategyFilter {
    @Autowired
    private BeaconCacheClient cacheClient;
    @Autowired
    private ErrorSendMsgUtil sendMsgUtil;

    @Override
    public void strategy(StandardSubmit submit) {
        log.info("【策略模块-敏感词校验】   校验ing…………");
        //1、 获取短信内容
        String text = submit.getText();

        //2、 对短信内容进行分词，并且将分析内容存储到集合中
        Set<String> contents = new HashSet<>();
        StringReader reader = new StringReader(text);
        IKSegmenter ik = new IKSegmenter(reader, false);
        Lexeme lex = null;
        while (true) {
            try {
                if ((lex = ik.next()) == null) {
                    break;
                }
            } catch (IOException e) {
                log.info("【策略模块-敏感词校验】   IK分词器在处理短信内容时，出现异常 e = {}", e.getMessage());
            }
            contents.add(lex.getLexemeText());
        }

        //3、 调用Cache缓存模块的交集方法，拿到结果

        Set<Object> dirtyWords = cacheClient.sinterStr(UUID.randomUUID().toString(),
                CacheConstant.DIRTY_WORD, contents.toArray(new String[]{}));
        //4、 根据返回的set集合，判断是否包含敏感词
        if (dirtyWords != null && dirtyWords.size() > 0) {
            //5、 如果有敏感词，抛出异常 / 其他操作。。
            log.info("【策略模块-敏感词校验】   短信内容包含敏感词信息， dirtyWords = {}", dirtyWords);
            // ================================发送写日志================================
            submit.setErrorMsg(ExceptionEnums.HAVE_DIRTY_WORD.getMsg() + "dirtyWords = " + dirtyWords.toString());
            sendMsgUtil.sendWriteLog(submit);

            // ================================发送状态报告的消息前，需要将report对象数据封装================================
            sendMsgUtil.sendPushReport(submit);
            // ================================抛出异常================================
            throw new StrategyException(ExceptionEnums.HAVE_DIRTY_WORD);
        }
    }
}
