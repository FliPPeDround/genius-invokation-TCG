package com.card.game.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.game.api.user.dto.SysUserDTO;
import com.card.game.common.redis.RedisCache;
import com.card.game.common.redis.constants.RedisPrefixConstant;
import com.card.game.common.result.ResultCode;
import com.card.game.common.web.exception.BizException;
import com.card.game.common.web.utils.BeanMapperUtils;
import com.card.game.mapper.SysUserMapper;
import com.card.game.pojo.dto.EmailRegisterDTO;
import com.card.game.pojo.entity.SysUserEntity;
import com.card.game.security.constant.SecurityConstants;
import com.card.game.security.enums.SecurityLoginType;
import com.card.game.security.support.userdetails.SecurityMailUserDetails;
import com.card.game.security.utils.JwtUtil;
import com.card.game.service.SysUserService;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tom
 * @since 2023-01-08
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {
    private final SysUserMapper sysUserMapper;

    private final RedisCache redisCache;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Map<String, Object> registerUser(EmailRegisterDTO emailRegisterDTO) {
        //判断用户是否已经注册
        if (isUserRegisteredByMailAccount(emailRegisterDTO.getEmail())) {
            throw new BizException(ResultCode.USER_IS_EXIST);
        }

        //TODO 校验邮箱验证码


        //校验两次密码是否一致
        if (!StringUtils.equals(emailRegisterDTO.getPassword(), emailRegisterDTO.getRePassword())) {
            throw new BizException(ResultCode.RE_PASSWORD_CHECK_ERROR);
        }
        //构造默认用户
        SysUserEntity defaultUser = SysUserEntity.buildDefaultUser();
        defaultUser.setEmail(emailRegisterDTO.getEmail());
        //密码加密
        defaultUser.setPassword(passwordEncoder.encode(emailRegisterDTO.getPassword()));

        SecurityMailUserDetails principal = new SecurityMailUserDetails(BeanMapperUtils.map(defaultUser, SysUserDTO.class));

        //存入数据库
        sysUserMapper.insert(defaultUser);

        //存入redis,1天后过期
        redisCache.setCacheObject(RedisPrefixConstant.AUTHENTICATION_PREFIX + principal.getMailAccount(),
                principal, 24, TimeUnit.HOURS);
        //生成token
        String token = JwtUtil.createJwt(emailRegisterDTO.getEmail(), SecurityLoginType.MAIL);
        Map<String, Object> userMap = Maps.newHashMap();
        //存放信息返回给前端
        userMap.put(SecurityConstants.AUTHORIZATION, token);
        //密码擦除
        principal.clearPassword();
        userMap.put(SecurityConstants.USER_INFO, principal.getSysUserDTO());
        return userMap;
    }

    @Override
    public Boolean isUserRegisteredByMailAccount(String mailAccount) {
        Long count = sysUserMapper
                .selectCount(Wrappers.<SysUserEntity>lambdaQuery()
                        .eq(SysUserEntity::getEmail, mailAccount));
        return count > 0;
    }
}
