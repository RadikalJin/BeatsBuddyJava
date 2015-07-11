package com.joe.task.service;

import com.joe.task.dao.jpa.TaskDao;
import com.joe.task.dao.jpa.TaskDaoImpl;
import com.joe.task.domain.Task;
import com.joe.task.dto.TaskDto;
import com.joe.task.util.TaskUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component(TaskServiceImpl.BEAN_NAME)
public class TaskServiceImpl implements TaskService {

	public static final String BEAN_NAME = "taskService";

	@Resource(name = TaskDaoImpl.BEAN_NAME)
	private TaskDao taskDao;


	/*
	* HIBERNATE
	* */
	@Transactional
 	public List<TaskDto> getAllPersistedTasks() throws Exception {
		return TaskUtil.convertTaskDomainsToDtos(taskDao.getAllPersistedTasks());
	}

	@Transactional
	public void persistTask(TaskDto taskDto) throws Exception {
		Task taskCandidate = TaskUtil.convertTaskDtoToDomain(taskDto);
		if (! taskIsAlreadyPersisted(taskCandidate)) {
			taskDao.persistTask(taskCandidate);
		}
	}

	@Transactional
	public TaskDto getTaskById(Integer taskId) throws Exception {
		Task taskById = taskDao.getTaskById(taskId);
		if (taskById == null) {
			return null;
		} else {
			return TaskUtil.convertTaskDomainToDto(taskById);
		}
	}

	@Transactional
	public void deleteTaskById(Integer taskId) {
		taskDao.deletePersistedTaskById(taskId);
	}

	@Transactional
	public List<TaskDto> getTasksByTaskTitle(String taskName) {
		return TaskUtil.convertTaskDomainsToDtos(taskDao.getTasksByTaskTitle(taskName));
	}

	@Transactional
	public void updateTask(final TaskDto taskDto) {
		Task task = TaskUtil.convertTaskDtoToDomain(taskDto);
		taskDao.updateTask(task);
	}

	public Boolean taskIsAlreadyPersisted(Task task) throws Exception {
		List<Task> allPersistedTasks = taskDao.getAllPersistedTasks();
		Boolean matchFound = false;
		for (Task allPersistedTask : allPersistedTasks) {
			if (task.equals(allPersistedTask)) {
				matchFound = true;
			}
		}
		return matchFound;
	}
}