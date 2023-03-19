package mishaninnikita.controllers;

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
  public HttpResponse<Task> addTaskToTable(@Body Task task) {
    if (!taskService.addTask(task)) {
      return HttpResponse.badRequest();
    }
    return HttpResponse.ok();
  }

  @Get
  @Produces(MediaType.APPLICATION_JSON)
  public HttpResponse<List<Task>> getTasks(HttpRequest<?> request) {
    String owner = request.getParameters().get("owner");
    String delete = request.getParameters().get("delete");
    String name = request.getParameters().get("name");
    String color = request.getParameters().get("color");

    if (name != null && delete != null && delete.equals("1")) {
      taskService.deleteByOwnerAndName(owner, name);
    } else if (name != null && owner != null && color != null) {
      taskService.changeColorTag(owner, name, color);
    }

    List<Task> taskList = (List<Task>) taskService.loadTasksByOwner(owner);
    return HttpResponse.ok().body(taskList);
  }
}
