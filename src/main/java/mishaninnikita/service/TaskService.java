package mishaninnikita.service;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import mishaninnikita.entity.Task;
import mishaninnikita.repository.TaskRepository;

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
        return taskRepository.findAllByOwner(owner);
    }

    public void deleteByOwnerAndName(String owner, String taskName) {
        try {
            taskRepository.deleteByOwnerAndName(owner, taskName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeColorTag(String owner, String taskName, String color) {
        Task task = taskRepository.findByOwnerAndName(owner, taskName);
        task.setColor(color);
        taskRepository.update(task);
    }
}
