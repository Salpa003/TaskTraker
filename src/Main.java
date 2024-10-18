import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

public class Main {
    public static void main(String[] args) {
    InMemoryTaskManager manager = (InMemoryTaskManager) Managers.getDefault();
    fillTask(manager);
    manager.printAllTask();
    manager.printHistory();
    }

    public static void fillTask(InMemoryTaskManager manager){
        Task task1 =new Task("Дз колледж (T)");
        Task task2 = new Task("Кубик рубик (T)");

        Epic epic1 = new Epic("Горизонт(Э)");
        Epic epic2 = new Epic("Сделать игру(Э)");

        Subtask subtask1 = new Subtask("Уголок");
        Subtask subtask2 = new Subtask("создать проект");
        epic1.addSubtask(subtask1, new Subtask("передний вис"), new Subtask("стойка на руках"));
        epic2.addSubtask(new Subtask("придумать сюжет"), new Subtask("планирование проекта"), new Subtask("поиск информации"),
                subtask2, new Subtask("презентация игры"));
        manager.addTask(task1, task2, epic1, epic2);

        manager.getTask(task1.getID());
        manager.getTask(task1.getID());
        manager.getTask(task1.getID());
        manager.getTask(task1.getID());
        manager.getTask(task1.getID());
        manager.getTask(task2.getID());

        manager.startTask(subtask1);
        manager.finishTask(subtask2);
    }
}
