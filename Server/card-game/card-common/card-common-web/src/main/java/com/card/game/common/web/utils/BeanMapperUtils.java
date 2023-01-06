package com.card.game.common.web.utils;


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author TomYou
 * @version v1.0 2022-07-31-9:26 AM
 */
public class BeanMapperUtils {

    private static final Mapper MAPPER;

    static {
        MAPPER = DozerBeanMapperBuilder.buildDefault();
    }

    public static <S, D> D map(S source, Class<D> destinationClass) {
        if (source == null) {
            return null;
        }
        return MAPPER.map(source, destinationClass);
    }

    public static <S, D> void copy(S source, D dest) {
        if (source == null) {
            return;
        }
        MAPPER.map(source, dest);
    }

    public static <S, D> List<D> mapList(final List<S> source, final Class<D> destType) {
        final List<D> dest = new ArrayList<D>();
        if (source == null || source.size() == 0) {
            return dest;
        }
        for (S element : source) {
            dest.add(MAPPER.map(element, destType));
        }
        return dest;
    }

    public static <S, D> Collection<D> mapCollection(final Collection<S> source, final Class<D> destType) {
        final Collection<D> dest = new ArrayList<>();
        if (source == null || source.size() == 0) {
            return dest;
        }
        for (S element : source) {
            dest.add(MAPPER.map(element, destType));
        }
        return dest;
    }

}

