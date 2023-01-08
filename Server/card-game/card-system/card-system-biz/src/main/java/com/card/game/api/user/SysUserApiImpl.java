package com.card.game.api.user;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.card.game.api.user.dto.SysUserDTO;
import com.card.game.common.web.utils.BeanMapperUtils;
import com.card.game.pojo.entity.SysUserEntity;
import com.card.game.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tomyou
 * @version v1.0 2023-01-08-4:30 PM
 */
@Service
@RequiredArgsConstructor
public class SysUserApiImpl implements SysUserApi {

    private final SysUserService sysUserService;

    @Override
    public SysUserDTO getUserByMailAccount(String mailAccount) {
        SysUserEntity sysUser = sysUserService.getOne(Wrappers.<SysUserEntity>lambdaQuery()
                .eq(SysUserEntity::getEmail, mailAccount));
        return BeanMapperUtils.map(sysUser, SysUserDTO.class);
    }

    @Override
    public Boolean register(SysUserDTO sysUserDTO) {
        SysUserEntity sysUserEntity = BeanMapperUtils.map(sysUserDTO, SysUserEntity.class);
        return sysUserService.save(sysUserEntity);
    }
}
