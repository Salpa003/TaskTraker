import taskStatus.TaskStatus;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private HashMap<Long, Task> tasks;

    public HashMap<Long, Task> getTasks() {
        return tasks;
    }

    public Manager() {
        tasks = new HashMap<>();
    }

    public void clearAllTasks() {
        tasks.clear();
        System.out.println("Все заддачи удаленны !");
    }

    public Task getTask(long ID) {
        return tasks.getOrDefault(ID, new Task("Error", "Error"));
    }

    public void addTask(Task... taskse) {
        for (Task task : taskse) {
            tasks.put(task.getID(), task);
        }
    }

    public void replaceTask(long ID, Task task) {
        tasks.replace(ID, task);
    }

    public void removeTask(Task task) {
        for (long key : tasks.keySet()) {
            if (tasks.get(key).equals(task)) {
                tasks.remove(key);
            }
        }
    }

    public void removeTask(long ID) {
        tasks.remove(ID);
    }

    public void startTask(Task task) {
        task.startTask();
    }

    public void finishTask(Task task) {
        task.finishTask();
    }

    public void printAllTask() {
        ArrayList<Task> newTask = new ArrayList<>();
        ArrayList<Task> inProgress = new ArrayList<>();
        ArrayList<Task> done = new ArrayList<>();

        for (var task : tasks.values()) {
            if (task.getStatus() == TaskStatus.NEW) {
                newTask.add(task);
            } else if (task.getStatus() == TaskStatus.IN_PROGRESS) {
                inProgress.add(task);
            } else if (task.getStatus() == TaskStatus.DONE) {
                done.add(task);
            }
        }
        System.out.println("|       NEW       |    IN_PROGRESS  |       DONE      |");

        for (int i = 0; i < tasks.size(); i++) {
            if (newTask.size() < i && inProgress.size() < i && inProgress.size() < i) {
                break;
            }
            if (newTask.size() <= i) {
                System.out.print(alignmentForTable(""));
            } else {
                System.out.print(alignmentForTable(newTask.get(i).getName()));
            }

            if (inProgress.size() <= i) {
                System.out.print(alignmentForTable(""));
            } else {
                System.out.print(alignmentForTable(inProgress.get(i).getName()));
            }

            if (done.size() <= i) {
                System.out.print(alignmentForTable(""));
            } else {
                System.out.print(alignmentForTable(done.get(i).getName()));
            }
            System.out.println("|");
        }

    }

    private String alignmentForTable(String string) {
        if (string==null){
            return "! null !";
        }
        String s = string;
        int l = 17;
        if (s.length() > l) {
            s = s.substring(0, l);
        } else {
            int a = 1;
            while (s.length() < l) {
                if (a == 1) {
                    s = " " + s;
                    a++;
                } else {
                    s = s + " ";
                    a = 1;
                }
            }
        }
        s = "|" + s;
        return s;
    }

}
