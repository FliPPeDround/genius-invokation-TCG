package com.card.game.controller;

import com.card.game.common.redis.RedisCache;
import com.card.game.common.result.Result;
import com.card.game.pojo.dto.EmailRegisterDTO;
import com.card.game.security.support.userdetails.SecurityMailUserDetails;
import com.card.game.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    private final SysUserService sysUserService;


    @GetMapping("/getUserInfo")
    public Result<SecurityMailUserDetails> getUserInfo() {
        SecurityMailUserDetails principal = (SecurityMailUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Result.success(principal);
    }


    @PostMapping("/mail/registerUser")
    public Result<Map<String, Object>> registerUser(@RequestBody @Validated EmailRegisterDTO emailRegisterDTO){
        Map<String, Object> userInfo = sysUserService.registerUser(emailRegisterDTO);
        return Result.success(userInfo);
    }

    @GetMapping("/mail/isUserRegistered/{mailAccount}")
    public Result<Boolean> isUserRegisteredByMailAccount(@PathVariable String mailAccount){
        Boolean flag =sysUserService.isUserRegisteredByMailAccount(mailAccount);
        return Result.success(flag);
    }
}
