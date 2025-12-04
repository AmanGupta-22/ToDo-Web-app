package com.app.todoapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.todoapp.models.Task;
import com.app.todoapp.repository.TaskRepository;

@Service
public class TaskService 
{
	@Autowired
    private TaskRepository taskRepository;
	
	public List<Task> getAllTasks() 
	{
		return taskRepository.findAll();
	}
	
	public void createTask(String title)
	   {
		 Task task = new Task();
		 task.setTitle(title);
		 task.setCompleted(false);
		 taskRepository.save(task);
		}
	
	public void deleteTask(Long id)
	{
		taskRepository.deleteById(id);
	}
	
	public void toggleTask(Long id)
	{
		Task task=taskRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Task id"));
		task.setCompleted(!task.isCompleted());
		taskRepository.save(task);
	}

	public Task getTaskById(Long id) 
	{
		return taskRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));
	}

	public void updateTask(Long id, String title) 
	{
		Task task = getTaskById(id);
		    task.setTitle(title);
		    taskRepository.save(task);
		}

		
	}

