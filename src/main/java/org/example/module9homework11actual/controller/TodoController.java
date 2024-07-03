package org.example.module9homework11actual.controller;

import lombok.RequiredArgsConstructor;
import org.example.module9homework11actual.entity.Todo;
import org.example.module9homework11actual.event.SaveLogEvent;
import org.example.module9homework11actual.repo.TodoRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepository todoRepository;
    private final ApplicationEventPublisher eventPublisher;


    @GetMapping
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }



//    @PostMapping
//    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
//        Todo savedTodo = todoRepository.save(todo);
//        return ResponseEntity.ok(savedTodo);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodoStatus(@PathVariable Integer id, @RequestBody Todo todoUpdate) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id " + id));

        todo.setCompleted(todoUpdate.getCompleted());
        Todo updatedTodo = todoRepository.save(todo);

        eventPublisher.publishEvent(new SaveLogEvent(todo));

        return ResponseEntity.ok(updatedTodo);
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        Todo savedTodo = todoRepository.save(todo);
        return ResponseEntity.ok(savedTodo);
    }
}
