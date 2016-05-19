package edu.bycheva.ToDo.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class Task extends Base{

    private String name;
    private TaskPriority taskPriority;
    private String description;
    private Date termEnd;

    public Task() {}

//    public Task(String name, TaskPriority taskPriority, String description, Date termEnd) {
//        this.name = name;
//        this.taskPriority = taskPriority;
//        this.description = description;
//        this.termEnd = termEnd;
//    }

    public Task(int id, String name, TaskPriority taskPriority, String description, Date termEnd) {
        this.id = id;
        this.name = name;
        this.taskPriority = taskPriority;
        this.description = description;
        this.termEnd = termEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    @Enumerated(EnumType.STRING)
    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority; // enum persisted as String in database
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIME)
    public Date getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(Date termEnd) {
        this.termEnd = termEnd;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taskPriority=" + taskPriority +
                ", description='" + description + '\'' +
                ", termEnd=" + termEnd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (taskPriority != task.taskPriority) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        return termEnd != null ? this.termEnd.compareTo(termEnd) == 0 : task.termEnd == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (taskPriority != null ? taskPriority.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (termEnd != null ? termEnd.hashCode() : 0);
        return result;
    }

}
