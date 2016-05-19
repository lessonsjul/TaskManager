package edu.bycheva.ToDo.store;

import edu.bycheva.ToDo.model.Task;
import edu.bycheva.ToDo.model.TaskPriority;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class JdbcStorageTest {
    @Test
    public void values() {

    }

    @Test
    public void addTest() throws IllegalStateException{
        final JdbcStorage storage = new JdbcStorage();
        final int id = storage.add(new Task(-1,"testTest", TaskPriority.NORMAL,"description",new Date()));
        final Task task = storage.get(id);
        assertEquals(id, task.getId());
        storage.delete(id);
        storage.close();
    }

    @Test
    public void editTest() throws Exception{
        final JdbcStorage storage = new JdbcStorage();
        final int id = storage.add(new Task(-1,"testTest", TaskPriority.NORMAL,"description",new Date()));
        Task editTask = new Task(id,"testTestEdited",TaskPriority.HIGHT,"description",new Date());
        storage.edit(editTask);
        assertEquals(editTask, storage.get(id));
        storage.close();
    }

    @Test(expected = IllegalStateException.class)
    public void deleteTest() throws IllegalStateException {
        final JdbcStorage storage = new JdbcStorage();
        final int id = storage.add(new Task(-1,"testTest", TaskPriority.NORMAL,"description",new Date()));
        storage.delete(id);
        storage.get(id);
        storage.close();
    }

    @Test(expected = IllegalStateException.class)
    public void get() throws IllegalStateException{
        final JdbcStorage storage = new JdbcStorage();
        storage.delete(1);
        storage.get(1);
        storage.close();
    }

    @Test
    public void generateId() {
        final JdbcStorage storage = new JdbcStorage();
        int id = storage.generateId();
        System.out.println(id);
    }

    @Test
    public void close() {

    }

}