
package com.taskflow;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Task> getTasks(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) String priority) {
        return repository.filterTasks(completed, priority);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(
            @Valid @RequestBody Task task) {
        task.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody Task input) {
        return repository.findById(id).map(task -> {
            task.setTitle(input.getTitle());
            task.setDescription(input.getDescription());
            task.setCompleted(input.isCompleted());
            task.setPriority(input.getPriority());
            return ResponseEntity.ok(repository.save(task));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Task> toggleTask(@PathVariable Long id) {
        return repository.findById(id).map(task -> {
            task.setCompleted(!task.isCompleted());
            return ResponseEntity.ok(repository.save(task));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
