package org.example.module9homework11actual.listeners;

import lombok.RequiredArgsConstructor;
import org.example.module9homework11actual.entity.Todo;
import org.example.module9homework11actual.entity.TodoLog;
import org.example.module9homework11actual.event.SaveLogEvent;
import org.example.module9homework11actual.repo.TodoLogRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
@RequiredArgsConstructor
public class TodoEventListener {

    private final TodoLogRepository todoLogRepository;


    @Async
    @EventListener
    public void generateListener(SaveLogEvent saveLogEvent) {
        Todo todo = saveLogEvent.todo();
        TodoLog todoLog = new TodoLog(todo.getId(), todo.getTitle(), todo.getDescription(), "Status changed to completed");
        todoLogRepository.save(todoLog);
    }
}
