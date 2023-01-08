package com.card.game.security.support.userdetails;

import com.card.game.api.user.SysUserApi;
import com.card.game.api.user.dto.SysUserDTO;
import com.card.game.security.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author tomyou
 * @version v1.0 2023-01-07-8:21 PM
 */
@Service
@RequiredArgsConstructor
public class SecurityMailUserDetailsServiceImpl extends UserDetailsServiceAdapter {

    private final SysUserApi sysUserApi;

    @Override
    public UserDetails loadUserByMailAccount(String mailAccount) {
        SecurityMailUserDetails userDetails = null;

        SysUserDTO userByMailAccount = sysUserApi.getUserByMailAccount(mailAccount);
        if (Objects.nonNull(userByMailAccount)){
            userDetails = new SecurityMailUserDetails(userByMailAccount);
        }else {
            //查出来的用户是空的则代表用户是第一次进行登陆,直接注册用户
            SysUserDTO sysUserDTO = SysUserDTO.builder()
                    .email(mailAccount)
                    .gender(2)
                    .avatar("")
                    .nickname("默认名称")
                    .lockFlag(false).build();
            sysUserApi.register(sysUserDTO);
            userDetails = new SecurityMailUserDetails(sysUserDTO);
        }

       if (!userDetails.isEnabled()) {
            throw new DisabledException(SecurityConstants.ACCOUNT_DISABLE);
        } else if (userDetails.isAccountNonLocked()) {
            throw new LockedException(SecurityConstants.ACCOUNT_LOCKED);
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException(SecurityConstants.ACCOUNT_EXPIRED);
        }
        return userDetails;
    }
}
