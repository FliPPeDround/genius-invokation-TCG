package com.card.game.controller;

import com.card.game.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tomyou
 * @version 1.0 created on 2023/1/6 12:39
 */
@RestController
public class TestController {

    @GetMapping
    public Result<?> test() {
        return Result.success();
    }
}
