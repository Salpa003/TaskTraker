package tasks;

import taskStatus.TaskStatus;

import java.util.HashMap;

public class Epic extends Task {
    private HashMap<Long, Subtask> subtasks;

    public Epic(String name) {
        super(name);
        subtasks = new HashMap<>();
    }

    public void addSubtask(Subtask...tasks) {
        for (Subtask task : tasks) {
            subtasks.put(task.getID(),  task);
        }
    }

    public HashMap<Long, Subtask> getSubtasks() {
        return subtasks;
    }

    public void updateStatus() {
        boolean isDone = true;
        boolean isNew = true;
        for (var task : subtasks.entrySet()) {
            if (task.getValue().getStatus() != TaskStatus.DONE) {
                isDone = false;
            }
            if (task.getValue().getStatus() != TaskStatus.NEW) {
                isNew = false;
            }
            if (subtasks.isEmpty()) {
                isNew = true;
            }
        }
        if (isDone) {
            setStatus(TaskStatus.DONE);
        } else if (isNew) {
            setStatus(TaskStatus.NEW);
        } else if (!isDone && !isNew) {
            setStatus(TaskStatus.IN_PROGRESS);
        }
    }

}
