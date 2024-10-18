import tasks.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    private ArrayList<Task> history;
    public  InMemoryHistoryManager() {
        history = new ArrayList<>();
    }

    @Override
    public void add(Task task) {
        history.add(task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return history; // или лучше использовать return new ArrayList<Task>(history);  ?
    }

    public void printHistory() {
        if (history.isEmpty()) {
            System.out.println("История пуста ");
        } else {
            System.out.println("История просмотренных задач :");
            for (int i = 0; i < history.size(); i++) {
                System.out.printf("%d) %s !\n",i+1,history.get(i).getName());
            }
        }
    }
}
