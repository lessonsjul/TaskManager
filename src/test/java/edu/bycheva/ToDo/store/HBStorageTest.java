package edu.bycheva.ToDo.store;

import edu.bycheva.ToDo.model.Task;
import edu.bycheva.ToDo.model.TaskPriority;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HBStorageTest {
    HibernateStorage hbstorage;
    final Task task = new Task(-1,"hibernate", TaskPriority.NORMAL,"description",new Date());

    @Before
    public void setHbstorage(){
        hbstorage = new HibernateStorage();
    }

    @After
    public void destroyHbstorage(){
        for(Object t: hbstorage.values()){
            hbstorage.delete(((Task)t).getId());
        }
        hbstorage.close();
    }


    Logger logger = Logger.getLogger("ClientApplicationLog");

    @Test
    public void values() {

    }

    @Test
    public void add() {
        final int id = hbstorage.add(task);
        assertEquals(id,hbstorage.get(id).getId());
    }

    @Test
    public void edit() {
        final int id = hbstorage.add(task);
        Task task2 = new Task(id,"hibernateEdit",TaskPriority.HIGHT,"new description",new Date());
        hbstorage.edit(task2);
        assertEquals(task2,hbstorage.get(id));
    }

    @Test
    public void get() {

    }

    @Test
    public void findByName() {

    }

    @Test
    public void findAllByName() {

    }

    @Test
    public void delete() {
        final int id = hbstorage.add(task);
        hbstorage.delete(id);
        assertNull(hbstorage.get(id));
    }

}
