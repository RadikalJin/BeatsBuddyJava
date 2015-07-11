package com.joe.task.service;

import com.joe.task.domain.Task;
import com.joe.task.dto.TaskDto;

import java.util.List;

public interface TaskService {

    public List<TaskDto> getAllPersistedTasks() throws Exception;
    public void persistTask(TaskDto taskDto) throws Exception;

    public TaskDto getTaskById(Integer taskId) throws Exception;
    public void deleteTaskById(Integer taskId);
    public List<TaskDto> getTasksByTaskTitle(String taskName);

    public void updateTask(final TaskDto taskDto);

}
