package com.app.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.todoapp.models.Task;
import com.app.todoapp.services.TaskService;

@Controller
public final class TaskController
{
	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public String getTasks(Model model)
	{
		List<Task> tasks=taskService.getAllTasks();
		model.addAttribute("tasks", tasks);
		return "tasks";
	}
	
	@PostMapping
	public String createTask(@RequestParam String title)
	{
		taskService.createTask(title);
		return "redirect:/";
	}
	
	@GetMapping("/{id}/delete")
	public String deleteTask(@PathVariable Long id)
	{
		taskService.deleteTask(id);
		return "redirect:/";
	}
	
	@GetMapping("/{id}/toggle")
	public String toggleTask(@PathVariable Long id)
	{
		taskService.toggleTask(id);
		return "redirect:/";
	}
	
	@GetMapping("/{id}/edit")
	public String editTask(@PathVariable Long id, Model model) {
	    Task task = taskService.getTaskById(id);
	    model.addAttribute("task", task);
	    return "edit"; // this opens edit.html
	}
	
	@PostMapping("/update")
	public String updateTask(@RequestParam Long id, @RequestParam String title) {
	    taskService.updateTask(id, title);
	    return "redirect:/";
	}





}
