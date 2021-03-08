package br.com.pedrodavi.todo.controller;

import br.com.pedrodavi.todo.model.Todo;
import br.com.pedrodavi.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
//@CrossOrigin("http://localhost:4200")
@CrossOrigin("https://pdrodavi.github.io/task-app")
public class TodoController {

    @Autowired
    private TodoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo save(@RequestBody Todo todo) {
        return repository.save(todo);
    }

    @GetMapping
    public List<Todo> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Todo getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PatchMapping("{id}/done")
    public Todo markAsDone(@PathVariable Long id) {
        return repository.findById(id).map(todo -> {
            todo.setDone(true);
            todo.setCompletionDate(LocalDateTime.now());
            repository.save(todo);
            return todo;
        }).orElse(null);
    }

}
