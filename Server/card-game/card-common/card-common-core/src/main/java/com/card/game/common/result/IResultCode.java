package com.card.game.common.result;

/**
 * @author TomYou
 * @version v1.0 2022-07-30-11:31 AM
 */
public interface IResultCode {
    /**
     * 获取响应Code
     *
     * @return Code
     */
    Integer getCode();

    /**
     * 获取响应消息
     *
     * @return Message
     */
    String getMessage();
}
