package com.card.game.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.card.game.pojo.dto.EmailRegisterDTO;
import com.card.game.pojo.entity.SysUserEntity;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tom
 * @since 2023-01-08
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 注册用户
     *
     * @param emailRegisterDTO 用户注册请求参数
     * @return 注册后的用户信息
     */
    Map<String, Object> registerUser(EmailRegisterDTO emailRegisterDTO);


    /**
     * 通过邮箱判断用户是否已经注册
     *
     * @param mailAccount 邮箱账户
     * @return 是否已注册
     */
    Boolean isUserRegisteredByMailAccount(String mailAccount);


}
