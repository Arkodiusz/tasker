package com.crud.tasker.service;

import com.crud.tasker.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("MESSAGE_CONTENT", message);
        context.setVariable("TASKER_URL", "http://Arkodiusz.github.io");
        context.setVariable("ADMIN_NAME", adminConfig.getAdminName());
        context.setVariable("BUTTON_NAME", "Visit website");

        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
