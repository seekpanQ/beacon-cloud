package com.mashibing.search.utils;

import java.time.LocalDateTime;

public class SearchUtils {

    /**
     * 索引前缀
     */
    public static final String INDEX = "sms_submit_log_";

    /**
     * 获取年份信息
     *
     * @return
     */
    public static String getYear() {
        return LocalDateTime.now().getYear() + "";
    }
}
