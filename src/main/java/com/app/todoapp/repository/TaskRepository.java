package com.app.todoapp.repository;

import com.app.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
// this takes care of all the crud operations
// task is the entity mapped to the database in sql