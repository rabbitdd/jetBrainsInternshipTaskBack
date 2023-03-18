package mishaninnikita.controllers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import mishaninnikita.data.Task;
import mishaninnikita.data.TaskService;

import java.util.ArrayList;

@Controller
@Secured(SecurityRule.IS_AUTHENTICATED)
public class TaskController {
  private final ArrayList<Task> tasks = new ArrayList<>();

  @Inject TaskService taskService;

  @Post("/mainContent")
  @Produces(MediaType.APPLICATION_JSON)
  public String addTaskToTable(@Body Task task) {
    if (taskService.addTask(task)) return "added";
    else return "not added";
  }

  @Get("/mainContent")
  @Produces(MediaType.APPLICATION_JSON)
  public ArrayList<Task> getTasks(HttpRequest<?> request) {
    String owner = request.getParameters().get("owner");
    String delete = request.getParameters().get("delete");
    String name = request.getParameters().get("name");
    String color = request.getParameters().get("color");
    if (name != null && delete != null && delete.equals("1")) {
      if (taskService.deleteByOwnerAndName(owner, name)) return tasks;
    } else if (name != null && owner != null && color != null) {
      taskService.changeColorTag(owner, name, color);
    }
    return (ArrayList<Task>) taskService.loadTasksByOwner(owner);
  }
}
