package com.mashibing.api.utils;

import com.mashibing.api.vo.ResultVO;
import com.mashibing.common.exception.ApiException;

/**
 * 返回结果
 */
public class R {

    public static ResultVO ok() {
        ResultVO r = new ResultVO();
        r.setCode(0);
        r.setMsg("接收成功");
        return r;
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO r = new ResultVO();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static ResultVO error(ApiException ex) {
        ResultVO r = new ResultVO();
        r.setCode(ex.getCode());
        r.setMsg(ex.getMessage());
        return r;
    }
}
