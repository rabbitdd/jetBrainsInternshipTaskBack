package mishaninnikita.data;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class TaskService {


    @Inject
    TaskRepository taskRepository;

    public boolean addTask(Task task) {
        try {
            taskRepository.save(task);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Iterable<Task> loadTasksByOwner(String owner) {
        Iterable<Task> tasks = taskRepository.findAllByOwner(owner);
        tasks.forEach(e -> System.out.println(e.toString()));
        return tasks;
    }

    public boolean deleteByOwnerAndName(String owner, String taskName) {
        try {
            taskRepository.deleteByOwnerAndName(owner, taskName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void changeColorTag(String owner, String taskName, String color) {
        Task task = taskRepository.findByOwnerAndName(owner, taskName);
        task.setColor(color);
        taskRepository.update(task);
    }
}
