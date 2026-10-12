
package com.taskflow;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;
    private boolean completed = false;
    private String priority = "MEDIUM";
    private LocalDateTime createdAt;

    public Task() {}

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        if (priority == null || priority.isBlank()) {
            priority = "MEDIUM";
        }
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
    public String getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
}
