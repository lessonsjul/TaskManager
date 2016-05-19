package edu.bycheva.ToDo.store;

import edu.bycheva.ToDo.model.Task;

import java.util.Collection;

public class TaskCache implements Storage{

    private static TaskCache INSTANCE = new TaskCache();

    private final Storage storage = new MemoryStorage();

    public static TaskCache getInstance() {
        return INSTANCE;
    }

    private TaskCache() {}

    public Collection<Task> values(){
        return this.storage.values();
    }

    public int add(Task task){
        return this.storage.add(task);
    }

    public void edit(Task task){
        this.storage.edit(task);
    }

    public void delete(int id){
        this.storage.delete(id);
    }

    public Task get(int id){
        return (Task)this.storage.get(id);
    }

    @Override
    public Task findByName(String name) {
        return null;
    }

    @Override
    public int generateId() {
        return this.storage.generateId();
    }

    @Override
    public void close() {
        this.storage.close();
    }
}
