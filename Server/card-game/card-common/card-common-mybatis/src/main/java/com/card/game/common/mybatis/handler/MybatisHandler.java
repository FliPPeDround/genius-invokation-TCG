package com.card.game.common.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author TomYou
 * @version v1.0 2022-07-30-3:31 PM
 */
@Slf4j
@Component
public class MybatisHandler implements MetaObjectHandler {
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";

    private static final String CREATE_BY = "createBy";

    private static final String UPDATE_BY = "updateBy";

    private static final String DEFAULT_CREATOR ="system";
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject,CREATE_BY,String.class,DEFAULT_CREATOR);
        this.strictInsertFill(metaObject,UPDATE_BY,String.class,DEFAULT_CREATOR);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject,UPDATE_BY,String.class,DEFAULT_CREATOR);
    }
}
