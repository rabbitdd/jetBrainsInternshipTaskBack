package mishaninnikita.controllers;

import io.micronaut.http.HttpParameters;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import mishaninnikita.entity.Task;
import mishaninnikita.service.TaskService;

import java.util.List;

@Controller("/task")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class TaskController {
  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @Post
  @Produces(MediaType.APPLICATION_JSON)
  public HttpResponse<Task> createTask(@Body Task task) {
    if (!taskService.addTask(task)) {
      return HttpResponse.badRequest();
    }
    return HttpResponse.ok();
  }

  @Get
  @Produces(MediaType.APPLICATION_JSON)
  public HttpResponse<List<Task>> getTasks(HttpRequest<?> request) {
    String owner = request.getParameters().get("owner");
    List<Task> taskList = (List<Task>) taskService.loadTasksByOwner(owner);
    return HttpResponse.ok().body(taskList);
  }

  @Post("/color")
  @Produces(MediaType.APPLICATION_JSON)
  public HttpResponse<List<Task>> changeTaskColor(@Body Task task) {
    taskService.changeColorTag(task.getOwner(), task.getName(), task.getColor());
    List<Task> taskList = (List<Task>) taskService.loadTasksByOwner(task.getOwner());
    return HttpResponse.ok().body(taskList);
  }

  @Delete
  @Produces(MediaType.APPLICATION_JSON)
  public HttpResponse<List<Task>> deleteTask(HttpRequest<?> request) {
    HttpParameters parameters = request.getParameters();
    taskService.deleteByOwnerAndName(parameters.get("owner"), parameters.get("name"));
    List<Task> taskList = (List<Task>) taskService.loadTasksByOwner(parameters.get("owner"));
    return HttpResponse.ok().body(taskList);
  }

}
