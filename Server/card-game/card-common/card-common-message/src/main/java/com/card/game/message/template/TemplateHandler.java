package com.card.game.message.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author tomyou
 * @version v1.0 2023-01-08-8:29 PM
 */
@Component
@RequiredArgsConstructor
public class TemplateHandler {
    private final TemplateEngine templateEngine;


    public String  getTemplateContent(String templateName){
        Context context = new Context();
        return templateEngine.process(templateName,context);
    }
}
