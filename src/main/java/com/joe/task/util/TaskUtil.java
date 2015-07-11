package com.joe.task.util;

import com.joe.task.domain.Task;
import com.joe.task.dto.TaskDto;

import java.util.*;

public class TaskUtil {

    public static TaskDto convertTaskDomainToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        if (task.getId() != null) {
            taskDto.setId(String.valueOf(task.getId()));
        }
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setDueDate(task.getDueDate());
        return taskDto;
    }

    public static List<TaskDto> convertTaskDomainsToDtos(List<Task> taskDomains) {
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task taskDomain : taskDomains) {
            taskDtos.add(convertTaskDomainToDto(taskDomain));
        }
        return taskDtos;
    }

    public static Task convertTaskDtoToDomain(TaskDto taskDto) {
        Task task = new Task();
        if (taskDto.getId() != null) {
            task.setId(Integer.parseInt(taskDto.getId()));
        }
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        return task;
    }

    public static List<Task> convertTaskDtosToDomains(List<TaskDto> taskDtos) {
        List<Task> tasks = new ArrayList<>();
        for (TaskDto taskDto : taskDtos) {
            tasks.add(convertTaskDtoToDomain(taskDto));
        }
        return tasks;
    }

    public static Calendar convertStringDateToSortableDate(String stringDate) {

        Map<String, Integer> months = new HashMap<>();
        months.put("January", 1);
        months.put("February", 2);
        months.put("March", 3);
        months.put("April", 4);
        months.put("May", 5);
        months.put("June", 6);
        months.put("July", 7);
        months.put("August", 8);
        months.put("September", 9);
        months.put("October", 10);
        months.put("November", 11);
        months.put("December", 12);

        String[] processedDate = stringDate.split(" ");
        Integer monthNumeric = months.get(processedDate[0]);
        Calendar releaseDate = Calendar.getInstance();
        releaseDate.set(2015, monthNumeric, Integer.parseInt(processedDate[1]));
        return releaseDate;
    }

    public static void log(Object aObject) {
        System.out.println(String.valueOf(aObject));
    }

}
