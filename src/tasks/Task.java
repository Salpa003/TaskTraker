package tasks;

import taskStatus.TaskStatus;

import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private long ID;

    private TaskStatus status;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        status = TaskStatus.NEW;
        ID = hashCode();
    }

    public Task(String name) {
      this(name,null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public long getID() {
        return ID;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        if (name != null) {
            hash += name.hashCode();
        }
        hash *= 31;
        if (description != null) {
            hash += description.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                (ID == task.ID) &&
                (status == task.status);

    }


    public void startTask() {
        if (status == TaskStatus.NEW) {
            status = TaskStatus.IN_PROGRESS;
            System.out.printf("Задача \"%s\" начата!\n", name);
        } else {
            System.out.printf("Задача \"%s\" уже выполняеться или выполнилась!\n", name);
        }
    }

    public void finishTask() {
        if (status != TaskStatus.DONE) {
            status = TaskStatus.DONE;
            System.out.printf("Задача \"%s\" законченна !\n", name);
        } else {
            System.out.printf("Задача \"%s\" уже законченна\n", name);
        }
    }

}
