package com.card.game.pojo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author tomyou
 * @version 1.0 created on 2023/1/12 11:18
 */
@Data
public class EmailRegisterDTO {

    @NotBlank(message = "邮箱账户不能为空")
    @Email(message = "请输入正确的邮箱类型")
    private String email;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 16)
    private String password;


    private String rePassword;

    @NotBlank(message = "邮箱验证码不能为空")
    private String mailCode;

}
