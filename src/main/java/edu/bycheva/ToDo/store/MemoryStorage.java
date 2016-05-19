package edu.bycheva.ToDo.store;

import edu.bycheva.ToDo.model.Task;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStorage implements Storage {

    private static final ConcurrentHashMap<Integer, Task> tasks = new ConcurrentHashMap();

    @Override
    public Collection<Task> values() {return this.tasks.values();}

    @Override
    public int add(Task model) {
        this.tasks.put(model.getId(), model);
        return model.getId();
    }

    @Override
    public void edit(Task model) {
        this.tasks.replace(model.getId(),model);
    }

    @Override
    public void delete(int id) {
        this.tasks.remove(id);
    }

    @Override
    public Task get(int id) {
        return this.tasks.get(id);
    }

    @Override
    public Task findByName(String name) {
        return null;
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {

    }
}
