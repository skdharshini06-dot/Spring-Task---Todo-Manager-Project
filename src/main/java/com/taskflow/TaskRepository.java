
package com.taskflow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("""
        SELECT t FROM Task t
        WHERE (:completed IS NULL OR t.completed = :completed)
        AND (:priority IS NULL OR LOWER(t.priority) = LOWER(:priority))
        """)
    List<Task> filterTasks(
        @Param("completed") Boolean completed,
        @Param("priority") String priority
    );
}
