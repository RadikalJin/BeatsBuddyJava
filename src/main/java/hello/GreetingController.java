package hello;

import com.joe.album.dto.AlbumDto;
import com.joe.album.service.AlbumService;
import com.joe.task.dto.TaskDto;
import com.joe.task.service.TaskService;
import com.joe.json.JsonResponse;
import com.joe.json.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    AlbumService albumService = (AlbumService) context.getBean("albumService");
    TaskService taskService = (TaskService) context.getBean("taskService");

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping("/prices")
    public List<Prices> prices() {
        List prices = new ArrayList();
        for (int i = 0; i < 5; i++) {
            prices.add(new Prices("NEW " + i, Math.random() * 1000, Math.random() * 1000));
        }
        return prices;
    }

    @RequestMapping("/setupAlbums")
    public String setupAlbums() throws Exception {
        List<AlbumDto> upcomingAlbums = albumService.retrieveUpcomingAlbums();
        albumService.persistAlbums(upcomingAlbums);
        return "Successfully setup albums";
    }

    @RequestMapping("/albums")
    public List<AlbumDto> albums() throws Exception {
        List<AlbumDto> persistedAlbums = albumService.getAllPersistedAlbums();
        return persistedAlbums;
    }

    @RequestMapping("/tasks")
    public List<TaskDto> tasks() throws Exception {
        List<TaskDto> persistedTasks = taskService.getAllPersistedTasks();
        return persistedTasks;
    }

    @RequestMapping("/saveTask")
    public String saveTask(@RequestParam(value = "name", defaultValue = "World") String name) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("Phone hotel");
        taskDto.setDescription("Must remember to reschedule the arrival date to after the announcement.");
        taskDto.setDueDate(Calendar.getInstance());
        try {
            taskService.persistTask(taskDto);
            return "Successfully persisted task.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception trying to persist task.";
        }
    }

    @RequestMapping(value="/createTask", method = {RequestMethod.POST})
    public String createTask(@RequestBody String task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("Phone hotel");
        taskDto.setDescription("Must remember to reschedule the arrival date to after the announcement.");
        taskDto.setDueDate(Calendar.getInstance());
        try {
            taskService.persistTask(taskDto);
            return "Successfully persisted task.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception trying to persist task.";
        }
    }

    @RequestMapping("/addNewTask")
    public String addNewTask(@RequestParam(value = "name", defaultValue = "World") String name) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("Phone hotel");
        taskDto.setDescription("Must remember to reschedule the arrival date to after the announcement.");
        taskDto.setDueDate(Calendar.getInstance());
        try {
            taskService.persistTask(taskDto);
            return "Successfully persisted task.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception trying to persist task.";
        }
    }


    @RequestMapping(value = "/addPerson",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"},
            produces="application/json")
    @ResponseBody
    public JsonResponse addPerson(@RequestBody Person person) {
        return new JsonResponse("OK","");
    }
}
