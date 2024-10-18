import tasks.Task;

import java.util.ArrayList;

public interface TaskManager {
    void finishTask(Task task);

    void startTask(Task task);

    void removeTask(Task task);

    void removeTask(long ID);

    void updateTask(long ID, Task task);

    void addTask(Task...task);
    void addTask(Task task);

    Task getTask(long ID);

    void clearAllTasks();

    ArrayList<Task> getHistory();
}
