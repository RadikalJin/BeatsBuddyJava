package com.joe.task.dao.jpa;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.joe.task.dao.jpa.*;
import com.joe.task.dao.jpa.TaskDao;
import com.joe.task.domain.Task;
import com.joe.task.util.TaskUtil;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component(TaskDaoImpl.BEAN_NAME)
public class TaskDaoImpl implements TaskDao {

	public static final String BEAN_NAME = "taskDao";

	private final static Charset ENCODING = StandardCharsets.UTF_8;

	@PersistenceContext
	private EntityManager em;


	/*
	* HIBERNATE
	* */
	public void persistTask(Task task) {
		em.persist(task);
	}

	public void persistTasks(List<Task> tasks) {
		for (Task task : tasks) {
			em.persist(task);
		}
	}

	public void updateTask(Task task) {
		Task existingVersionOfTask = em.find(Task.class, task.getId());
		existingVersionOfTask.setTitle(task.getTitle());
		existingVersionOfTask.setDescription(task.getDescription());
		existingVersionOfTask.setDueDate(task.getDueDate());
		em.persist(existingVersionOfTask);
	}

	public List<Task> getAllPersistedTasks() {
		return em.createQuery("SELECT a FROM Task a", Task.class).getResultList();
	}

	public Task getTaskById(Integer taskId) {
		return em.find(Task.class, taskId);
	}

	public void deletePersistedTaskById(Integer taskId) {
		Task task = em.find(Task.class, taskId);
		if (task != null) {
			em.remove(task);
		}
	}

	public void deleteAllPersistedTasks() {
		em.createQuery("DELETE from Task").executeUpdate();
	}

	public List<Task> getTasksByTaskTitle(String taskName) {
		return em.createQuery("SELECT a FROM Task a WHERE title = '" + taskName + "'", Task.class).getResultList();
	}

}

