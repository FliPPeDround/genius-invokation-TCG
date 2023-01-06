package com.card.game.common.web.exception;


import com.card.game.common.result.IResultCode;
import lombok.Getter;

/**
 * @author TomYou
 * @version v1.0 2022-07-31-9:16 AM
 */
@Getter
public class BizException extends RuntimeException{

    public IResultCode resultCode;

    public BizException(IResultCode errorCode) {
        super(errorCode.getMessage());
        this.resultCode = errorCode;
    }

    public BizException(String message){
        super(message);
    }

    public BizException(String message, Throwable cause){
        super(message, cause);
    }

    public BizException(Throwable cause){
        super(cause);
    }
}