package com.card.game.service;

import com.card.game.api.user.vo.SysUserVO;
import com.card.game.pojo.dto.RegisterUserDTO;
import com.card.game.pojo.entity.SysUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @param registerUserDTO 用户注册请求参数
     * @return 注册后的用户信息
     */
    SysUserVO registerUser(RegisterUserDTO registerUserDTO);


    /**
     * 判断用户是否已经注册
     *
     * @param mailAccount 邮箱账户
     * @return 是否已注册
     */
    Boolean getIsUserRegistered(String mailAccount);


}
