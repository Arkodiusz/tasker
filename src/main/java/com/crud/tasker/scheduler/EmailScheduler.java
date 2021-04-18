package com.crud.tasker.scheduler;

import com.crud.tasker.config.AdminConfig;
import com.crud.tasker.domain.Mail;
import com.crud.tasker.repository.TaskRepository;
import com.crud.tasker.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";

    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    @Scheduled(fixedDelay = 60000)
    //@Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String endOfMessage = size != 1 ? "tasks" : "task";
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "Currently in database you got: " + size + " " + endOfMessage + ".",
                        null
                )
        );
    }
}
