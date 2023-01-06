package com.card.game.common.base;


import cn.hutool.core.util.ObjectUtil;

import java.util.EnumSet;
import java.util.Objects;

/**
 * 枚举通用接口
 *
 * @author tomyou
 */
public interface IBaseEnum<T> {

    /**
     * 根据值获取枚举
     *
     * @return T
     */
    T getValue();

    /**
     * 根据文本标签获取值
     *
     * @return String
     */
    String getLabel();

    /**
     * 根据值获取枚举
     *
     * @param value 值
     * @param clazz 枚举类型
     * @param <E>   枚举
     * @return E
     */
    static <E extends Enum<E> & IBaseEnum> E getEnumByValue(Object value, Class<E> clazz) {
        Objects.requireNonNull(value);
        // 获取类型下的所有枚举
        EnumSet<E> allEnums = EnumSet.allOf(clazz);
        return allEnums.stream()
                .filter(e -> ObjectUtil.equal(e.getValue(), value))
                .findFirst()
                .orElse(null);
    }

    /**
     * 根据文本标签获取值
     *
     * @param value 值
     * @param clazz 枚举类型
     * @param <E>   枚举
     * @return 文本标签
     */
    static <E extends Enum<E> & IBaseEnum> String getLabelByValue(Object value, Class<E> clazz) {
        Objects.requireNonNull(value);
        // 获取类型下的所有枚举
        EnumSet<E> allEnums = EnumSet.allOf(clazz);
        E matchEnum = allEnums.stream()
                .filter(e -> ObjectUtil.equal(e.getValue(), value))
                .findFirst()
                .orElse(null);

        String label = null;
        if (matchEnum != null) {
            label = matchEnum.getLabel();
        }
        return label;
    }


    /**
     * 根据文本标签获取值
     *
     * @param label 标签
     * @param clazz 类型
     * @param <E>   e
     * @return 枚举
     */
    static <E extends Enum<E> & IBaseEnum> Object getValueByLabel(String label, Class<E> clazz) {
        Objects.requireNonNull(label);
        // 获取类型下的所有枚举
        EnumSet<E> allEnums = EnumSet.allOf(clazz);
        E matchEnum = allEnums.stream()
                .filter(e -> ObjectUtil.equal(e.getLabel(), label))
                .findFirst()
                .orElse(null);

        Object value = null;
        if (matchEnum != null) {
            value = matchEnum.getValue();
        }
        return value;
    }


}
