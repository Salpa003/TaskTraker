import taskStatus.TaskStatus;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private InMemoryHistoryManager historyManager;
    private HashMap<Long, Task> tasks;

    public HashMap<Long, Task> getTasks() {
        return tasks;
    }

    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        historyManager = (InMemoryHistoryManager) Managers.getDefaultHistory();
    }


    public void clearAllTasks() {
        tasks.clear();
        System.out.println("Все заддачи удаленны !");
    }

    @Override
    public ArrayList<Task> getHistory() {
        return null;
    }

    public Task getTask(long ID) {
        Task task = tasks.getOrDefault(ID, null);
        if (task != null)
            historyManager.add(task);
        return tasks.getOrDefault(ID, new Task("Error", "Error"));
    }

    public void addTask(Task... taskse) {
        for (Task task : taskse) {
            tasks.put(task.getID(), task);
        }
    }

    public void addTask(Task task) {
        tasks.put(task.getID(), task);
    }

    public void updateTask(long ID, Task task) {
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
            if (task instanceof Epic){
                ((Epic) task).updateStatus();
            }
            if (task.getStatus() == TaskStatus.NEW) {
                newTask.add(task);
            } else if (task.getStatus() == TaskStatus.IN_PROGRESS) {
                inProgress.add(task);
            } else if (task.getStatus() == TaskStatus.DONE) {
                done.add(task);
            }
        }
        System.out.println("All tasks");
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
        System.out.println();

        for (var task : tasks.entrySet()) {
            Epic epic;
            if (task.getValue() instanceof Epic) {
                 epic = (Epic) task.getValue();
            } else  {
                continue;
            }

            ArrayList<Subtask> n = new ArrayList<>();
            ArrayList<Subtask> i = new ArrayList<>();
            ArrayList<Subtask> d = new ArrayList<>();

            for (var taskS : epic.getSubtasks().values()) {
                if (taskS.getStatus() == TaskStatus.NEW) {
                    n.add(taskS);
                } else if (taskS.getStatus() == TaskStatus.IN_PROGRESS) {
                    i.add(taskS);
                } else if (taskS.getStatus() == TaskStatus.DONE) {
                    d.add(taskS);
                }
            }

                System.out.println(epic.getName());
                System.out.println("|       NEW       |    IN_PROGRESS  |       DONE      |");
                for (int k = 0; k < epic.getSubtasks().size(); k++) {
                    if (n.size() < k && i.size() < k && i.size() < k) {
                        break;
                    }
                    if (n.size() <= k) {
                        System.out.print(alignmentForTable(""));
                    } else {
                        System.out.print(alignmentForTable(n.get(k).getName()));
                    }

                    if (i.size() <= k) {
                        System.out.print(alignmentForTable(""));
                    } else {
                        System.out.print(alignmentForTable(i.get(k).getName()));
                    }

                    if (d.size() <= k) {
                        System.out.print(alignmentForTable(""));
                    } else {
                        System.out.print(alignmentForTable(d.get(k).getName()));
                    }
                    System.out.println("|");
                }
            System.out.println();
        }
    } // незнаю как разбить этот метод на более мелкие , чтобы не копировать код

    private String alignmentForTable(String string) {
        if (string == null) {
            return "! null !";
        }
        String s = string;
        int l = 17; // кол-во символов между |~~~| таблицы
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

    public void printHistory() {
        historyManager.printHistory();
    }
}
