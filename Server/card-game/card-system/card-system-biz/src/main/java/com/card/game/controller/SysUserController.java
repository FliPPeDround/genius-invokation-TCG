package com.card.game.controller;

import com.card.game.api.user.vo.SysUserVO;
import com.card.game.common.redis.RedisCache;
import com.card.game.common.result.Result;
import com.card.game.pojo.dto.RegisterUserDTO;
import com.card.game.security.support.userdetails.SecurityMailUserDetails;
import com.card.game.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/registerUser")
    public Result<SysUserVO> registerUser(@RequestBody RegisterUserDTO registerUserDTO){
        SysUserVO sysUserVO = sysUserService.registerUser(registerUserDTO);
        return Result.success(sysUserVO);
    }

    @GetMapping("/mail/isUserRegistered/{mailAccount}")
    public Result<Boolean> isUserRegistered(@PathVariable String mailAccount){
        Boolean flag =sysUserService.getIsUserRegistered(mailAccount);
        return Result.success(flag);
    }
}
