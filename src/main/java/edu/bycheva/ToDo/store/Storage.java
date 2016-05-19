package edu.bycheva.ToDo.store;

import edu.bycheva.ToDo.model.Task;

import java.util.Collection;

public interface Storage {

    public Collection values();

    public int add(Task model);

    public void edit(Task model);

    public void delete(int id);

    public Task get(int id);

    public Task findByName(String name);

    public int generateId();

    public void close();
}
