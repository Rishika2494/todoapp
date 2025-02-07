package com.app.todoapp.service;

import com.app.todoapp.models.Task;
import com.app.todoapp.repository.TaskRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class TaskService {
 private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
  return taskRepository.findAll();

    }

    public void createTask(String title,int priority,String category)
    {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        task.setPriority(priority); // Set priority
        task.setCategory(category); // Set category
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTask(Long id) {
        Task task;
        task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Task id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }


    public List<Task> getTasksSortedByDirection(String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by("priority").descending(): Sort.by("priority").ascending();
        List<Task> list= taskRepository.findAll(sort);
        return list;
    }
}
