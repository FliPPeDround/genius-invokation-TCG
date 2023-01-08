package com.card.game.controller;

import com.card.game.common.redis.RedisCache;
import com.card.game.common.redis.constants.RedisPrefixConstant;
import com.card.game.common.result.Result;
import com.card.game.security.support.userdetails.SecurityMailUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户控制器
 * </p>
 *
 * @author tom
 * @since 2023-01-08
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class SysUserController {

    private final RedisCache redisCache;

    @PostMapping("/getCode")
    public Result<String> getCode() {
        redisCache.setCacheObject(RedisPrefixConstant.MAIL_CODE_PREFIX + "1667213197@qq.com", "2022");
        return Result.success();
    }


    @GetMapping("/getUserInfo")
    public Result<SecurityMailUserDetails> getUserInfo() {
        SecurityMailUserDetails principal = (SecurityMailUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Result.success(principal);
    }
}
