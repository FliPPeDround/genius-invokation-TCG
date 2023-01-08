package com.card.game.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tomyou
 * @version v1.0 2023-01-08-4:52 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserDTO {

    /**
     * 用户Id
     */
    private Long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户性别((1:男;0:女,2:未知))
     */
    private Integer gender;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户邮箱帐号
     */
    private String email;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 0-正常，1-锁定
     */
    private Boolean lockFlag;
}
