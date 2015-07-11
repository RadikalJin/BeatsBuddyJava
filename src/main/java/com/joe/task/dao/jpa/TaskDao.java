package com.joe.task.dao.jpa;

import java.util.List;

import com.joe.task.domain.Task;

public interface TaskDao {

	public void persistTask(Task task);
	public List<Task> getAllPersistedTasks() throws Exception;
	public Task getTaskById(Integer taskId) throws Exception;
	public void deletePersistedTaskById(Integer taskId);
	public void updateTask(Task task);
	public List<Task> getTasksByTaskTitle(String taskName);

}
