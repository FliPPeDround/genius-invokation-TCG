package com.card.game;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tomyou
 * @version v1.0 2023-01-07-7:49 PM
 */
@SpringBootApplication
@MapperScan("com.card.game.mapper")
public class CardGameApplication {
    public static void main(String[] args) {
        SpringApplication.run(CardGameApplication.class, args);
    }
}
