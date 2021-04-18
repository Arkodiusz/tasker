package com.crud.tasker.service;

import com.crud.tasker.config.AdminConfig;
import com.crud.tasker.domain.Task;
import com.crud.tasker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("MESSAGE_CONTENT", message);
        context.setVariable("TASKER_URL", "http://Arkodiusz.github.io");
        context.setVariable("adminConfig", adminConfig);
        context.setVariable("BUTTON_NAME", "Visit website");
        context.setVariable("PREVIEW_MESSAGE", "NOTIFICATION");
        context.setVariable("GOODBYE_MESSAGE", "Bye bye!");

        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);

        context.setVariable("application_functionality", functionality);

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailyEmail(String message) {

        List<String> taskList = new ArrayList<>();

        for (Task task : taskRepository.findAll()) {
            taskList.add(task.getTitle());
        }

        Context context = new Context();

        context.setVariable("preview_msg", "NOTIFICATION");
        context.setVariable("message", message); context.setVariable("goodbye_msg", "Get the work done!");
        context.setVariable("adminConfig", adminConfig);
        context.setVariable("tasker_url", "http://Arkodiusz.github.io");
        context.setVariable("task_list", taskList);

        return templateEngine.process("mail/daily-notification-mail", context);
    }
}
