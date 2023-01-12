package com.card.game.security.enums;

import com.card.game.common.base.IBaseEnum;
import lombok.AllArgsConstructor;

/**
 * @author tomyou
 * @version v1.0 2023-01-08-8:59 PM
 */

@AllArgsConstructor
public enum SecurityLoginType implements IBaseEnum<String> {


    MAIL("邮箱登陆", "mailType")
    ;
    private String typeName;

    private String typeCode;
    @Override
    public String getValue() {
        return this.typeCode;
    }

    @Override
    public String getLabel() {
        return this.typeName;
    }
}
