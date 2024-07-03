package org.example.module9homework11actual.component;

import lombok.RequiredArgsConstructor;
import org.example.module9homework11actual.entity.Todo;
import org.example.module9homework11actual.repo.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final TodoRepository todoRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 10; i++) {
            Todo todo = new Todo();
            todo.setId(i);
            todo.setCompleted(false);
            todo.setTitle("Todo Title " + i);
            todo.setDescription("This is the description for Todo " + i);
            todoRepository.save(todo);
        }
    }
}
