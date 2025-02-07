package com.app.todoapp.controller;
import com.app.todoapp.models.Task;
import com.app.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private final TaskService taskService;
// constructor injection

    public TaskController(TaskService taskService)
    {
        this.taskService= taskService;
    }
    @GetMapping
    public String getTasks(Model model)
    {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks",tasks);
        return "tasks";
    }

    @PostMapping
    public String createTask(@RequestParam String title, @RequestParam(defaultValue = "1") int priority,@RequestParam(defaultValue = "General") String category )// Default: General)
    {
        taskService.createTask(title,priority,category);
        // this is to redirect to the main tasks page
        return "redirect:/";

    }

    @GetMapping("{id}/delete")
    public String deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @GetMapping("{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleTask(id);
        return "redirect:/";
    }
    @ResponseBody
    @GetMapping("/sort/{direction}")
    public List<Task> sortTasks(@PathVariable String direction) {
        List<Task> list =taskService.getTasksSortedByDirection(direction);
        return list;
    }

    }



