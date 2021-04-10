package mishaninnikita.controllers;


import io.micronaut.context.annotation.Parameter;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import mishaninnikita.data.Task;
import mishaninnikita.data.TaskService;

import javax.inject.Inject;
import java.util.ArrayList;

@Controller
@Secured(SecurityRule.IS_AUTHENTICATED)
public class TaskController {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    @Inject
    TaskService taskService;

    @Post("/mainContent")
    @Produces(MediaType.APPLICATION_JSON)
    public String addTaskToTable(@Body Task task) {
        if (taskService.addTask(task))
            return "added";
        else
            return "not added";
    }

    @Get("/mainContent")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Task> getTasks(
            HttpRequest<?> request) {
        String owner = request.getParameters().get("owner");
        String delete = request.getParameters().get("delete");
        String name = request.getParameters().get("name");
        String color = request.getParameters().get("color");
        System.out.println(request.getParameters().get("owner"));
        System.out.println("get");
        if (name != null && delete != null && delete.equals("1")) {
            System.out.println(name + " " + delete);
            if(taskService.deleteByOwnerAndName(owner, name))
                System.out.println("delete");
            return tasks;
        } else if (name != null && owner != null && color != null){
            System.out.println("change color");
            taskService.changeColorTag(owner, name, color);

        }
        return (ArrayList<Task>) taskService.loadTasksByOwner(owner);


    }



}
