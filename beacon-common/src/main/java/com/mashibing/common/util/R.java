package com.mashibing.common.util;

import com.mashibing.common.enums.ExceptionEnums;
import com.mashibing.common.vo.ResultVO;

/**
 * 封装ResultVO的工具
 */
public class R {

    /**
     * 成功，无数据
     *
     * @return
     */
    public static ResultVO ok() {
        return new ResultVO(0, "");
    }

    /**
     * 成功，有数据
     *
     * @param data
     * @return
     */
    public static ResultVO ok(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 失败，指定错误信息
     * @param enums
     * @return
     */
    public static ResultVO error(ExceptionEnums enums) {
        return new ResultVO(enums.getCode(), enums.getMsg());
    }


}
