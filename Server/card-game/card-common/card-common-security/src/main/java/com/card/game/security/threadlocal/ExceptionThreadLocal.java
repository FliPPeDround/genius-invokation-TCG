package com.card.game.security.threadlocal;


import com.card.game.common.result.ResultCode;
import org.springframework.stereotype.Component;

/**
 * @author tomyou
 * @version 1.0 created on 2022/10/19 9:44
 */
@Component
public class ExceptionThreadLocal {
    private static final ThreadLocal<ResultCode> CODE_HOLDER = new ThreadLocal<>();


    public void setResultCode(ResultCode resultCode) {
        CODE_HOLDER.set(resultCode);
    }

    public ResultCode getResultCode() {
        ResultCode resultCode = CODE_HOLDER.get();
        if (resultCode == null) {
            resultCode = ResultCode.UNAUTHORIZED;
            CODE_HOLDER.set(resultCode);
        }
        return resultCode;
    }

    public void clearContext() {
        CODE_HOLDER.remove();
    }
}
