package com.card.game.common.web.exception;

import com.card.game.common.result.Result;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author TomYou
 * @version v1.0 2022-07-31-9:20 AM
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({BizException.class})
    public Result<String> bizException(BizException e) {
        log.error("业务异常,异常原因:{}", e.getMessage(), e);
        return Result.error(e.getResultCode());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("未知异常，异常原因：{}", e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("非法参数异常，异常原因：{}", e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(JsonProcessingException.class)
    public Result<String> handleJsonProcessingException(JsonProcessingException e) {
        log.error("Json转换异常，异常原因：{}", e.getMessage(), e);
        return Result.error(e.getMessage());
    }
}
