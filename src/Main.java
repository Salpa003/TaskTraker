import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Task task1 = new Task("Уборка", "Убрать комноту");
        Task task2 = new Task("Работа");
        Epic epic = new Epic("Epic");
        Subtask subtask = new Subtask("подзадача");

        task1.startTask();
        task2.finishTask();

        epic.addSubtask(subtask);
        subtask.finishTask();
        epic.updateStatus();

        manager.addTask(task1, task2, epic);
        manager.printAllTask();
    }
}
