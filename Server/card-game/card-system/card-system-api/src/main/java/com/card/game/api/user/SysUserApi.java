package com.card.game.api.user;

import com.card.game.api.user.dto.SysUserDTO;

/**
 * @author tomyou
 * @version v1.0 2023-01-08-4:25 PM
 */
public interface SysUserApi {

    /**
     * 根据邮箱号查询用户
     *
     * @param mailAccount 邮箱帐号
     * @return SysUserDTO
     */
    SysUserDTO getUserByMailAccount(String mailAccount);


    /**
     * 用户注册
     *
     * @param sysUserDTO 用户
     * @return 是否注册成功
     */
    Boolean register(SysUserDTO sysUserDTO);
}
