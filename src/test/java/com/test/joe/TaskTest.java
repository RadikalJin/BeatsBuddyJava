package com.test.joe;

import com.joe.task.dto.TaskDto;
import com.joe.task.service.TaskService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class TaskTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
	TaskService taskService = (TaskService) context.getBean("taskService");
	List<TaskDto> testTasks;
	Boolean keepData = false;

/*	@Resource(name = TaskServiceImpl.BEAN_NAME)
	private TaskService taskService;*/

/*	@Resource(name = TaskServiceImpl.BEAN_NAME)
	private TaskService taskService;*/

	@Before
	public void setUp() throws Exception {
		testTasks = new ArrayList<>();
		TaskDto testTask1 = new TaskDto();
		testTask1.setTitle("TEST_TASK_1");
		testTask1.setDescription("TEST_PASSWORD_1");
		testTask1.setDueDate(Calendar.getInstance());
		TaskDto testTask2 = new TaskDto();
		testTask2.setTitle("TEST_TASK_2");
		testTask2.setDescription("TEST_PASSWORD_2");
		testTask2.setDueDate(Calendar.getInstance());
		TaskDto testTask3 = new TaskDto();
		testTask3.setTitle("TEST_TASK_3");
		testTask3.setDescription("TEST_PASSWORD_3");
		testTask3.setDueDate(Calendar.getInstance());
		testTasks.add(testTask1);
		testTasks.add(testTask2);
		testTasks.add(testTask3);
	}


	/*
	*  PERSISTENCE
	*/
	@Test
	public void testPersistingSingleTaskUsingHibernate() throws Exception {
		TaskDto testTask = testTasks.get(0);
		try {
			taskService.getTasksByTaskTitle(testTask.getTitle());
		} catch (NoResultException e) {
			System.out.println("Caught exception as expected when finding task by name, when does not exist");
		}
		taskService.persistTask(testTask);
		assertNotNull(taskService.getTasksByTaskTitle(testTask.getTitle()));
	}

/*	@Test
	public void testUpdatingPersistedTaskUsingHibernate() throws Exception {
		TaskDto prePersistTask = testTasks.get(0);
		taskService.persistTask(prePersistTask);
		TaskDto postPersistTask = taskService.getTasksByTaskTitle(prePersistTask.getTitle());
		assertNotNull(postPersistTask);
		assertEquals(prePersistTask.getPassword(), postPersistTask.getPassword());
		postPersistTask.setPassword("Something new");
		taskService.updateTask(postPersistTask);
		TaskDto updatedTask = taskService.getTaskByTaskname(prePersistTask.getTaskname());
		assertNotNull(updatedTask);
		assertNotEquals(prePersistTask.getPassword(), postPersistTask.getPassword());
	}

	@Test
	public void testGetPersistedTaskById() throws Exception {
		taskService.persistTasks(testTasks);
		TaskDto persistedTask = taskService.getAllTasks().get(0);
		assertNotEquals(null, taskService.getTaskById(Integer.parseInt(persistedTask.getId())));
	}

	@Test
	public void testDeletePersistedTaskById() throws Exception {
		taskService.persistTasks(testTasks);
		TaskDto persistedTask = taskService.getAllTasks().get(0);
		Integer persistedTaskId = Integer.parseInt(persistedTask.getId());
		assertNotNull(taskService.getTaskById(persistedTaskId));
		taskService.deleteTaskById(persistedTaskId);
		assertNull(taskService.getTaskById(persistedTaskId));
	}

	@Test
	public void testGetTasksByTaskId() throws Exception {
		taskService.persistTasks(testTasks);
		TaskDto persistedTask = taskService.getAllTasks().get(0);
		TaskDto testTask = new TaskDto();
		testTask.setArtist("TEST_ARTIST");
		testTask.setTitle("TEST_TITLE");
		testTask.setReleaseDate(Calendar.getInstance());
		taskService.persistTask(testTask);
		List<TaskDto> tasksByArtistName = taskService.getTasksByArtistName(testTask.getArtist());
		TaskDto persistedTask = tasksByArtistName.get(0);
		taskService.persistTaskToTask(Integer.parseInt(persistedTask.getId()), Integer.parseInt(persistedTask.getId()));
		List<TaskTaskDto> taskTasksByTaskId = taskService.getTaskTasksByTaskId(Integer.parseInt(persistedTask.getId()));
		assertTrue(taskTasksByTaskId.size() > 0);
	}
	*/

}