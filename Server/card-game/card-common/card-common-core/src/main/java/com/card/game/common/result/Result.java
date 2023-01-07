package com.card.game.common.result;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author TomYou
 * @version v1.0 2022-07-30-11:31 AM
 */
@Data
public class Result<T> implements Serializable {
    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应的消息
     */
    private String message;

    /**
     * 响应的数据
     */
    private T data;

    /**
     * 时间戳
     */
    private LocalDateTime timestamp;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setTimestamp(LocalDateTime.now());
        return result;
    }


    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        result.setTimestamp(LocalDateTime.now());
        return result;
    }

    public static <T> Result<T> success(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setTimestamp(LocalDateTime.now());
        return result;
    }

    public static <T> Result<T> success(IResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setTimestamp(LocalDateTime.now());
        return result;
    }

    public static <T> Result<T> success(IResultCode resultCode, T data) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setTimestamp(LocalDateTime.now());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.ERROR.getCode());
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setTimestamp(LocalDateTime.now());
        return result;
    }

    public static <T> Result<T> error(IResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setTimestamp(LocalDateTime.now());
        return result;
    }


    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setTimestamp(LocalDateTime.now());
        return result;
    }

    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.ERROR.getCode());
        result.setMessage(message);
        result.setTimestamp(LocalDateTime.now());
        return result;
    }

}
