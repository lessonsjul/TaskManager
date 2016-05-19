package edu.bycheva.ToDo.store;

import edu.bycheva.ToDo.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Collection;

public class HibernateStorage implements Storage {
    private final SessionFactory factory;

    public HibernateStorage() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Collection values() {
        final Session session = this.factory.openSession();
        Transaction trx = session.beginTransaction();
        try{
            return session.createQuery("from Task").list();
        }finally {
            trx.commit();
            session.close();
        }
    }

    @Override
    public int add(Task model) {
        final Session session = this.factory.openSession();
        Transaction trx = session.beginTransaction();
        try{
            session.save(model);
            return model.getId();
        }finally {
            trx.commit();
            session.close();
        }

    }

    @Override
    public void edit(Task model) {
        final Session session = this.factory.openSession();
        Transaction trx = session.beginTransaction();
        try {
            session.update(model);
        }finally {
            trx.commit();
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        final Session session = this.factory.openSession();
        Transaction trx = session.beginTransaction();
        try{
            session.delete(new Task(id,null,null,null,null));
        }finally {
            trx.commit();
            session.close();
        }
    }

    @Override
    public Task get(int id) {
        final Session session = this.factory.openSession();
        Transaction trx = session.beginTransaction();
        try{
            return (Task)session.get(Task.class,id);
        }finally {
            trx.commit();
            session.close();
        }
    }

    @Override
    public Task findByName(String name) {
        final Session session = this.factory.openSession();
        Transaction trx = session.beginTransaction();
        try{
            final Query query = session.createQuery("from Task as task  where task.name = :name");
            query.setString("name",name);
            return (Task) query.iterate().next();
        }finally {
            trx.commit();
            session.close();
        }
    }

    public Task findAllByName(String name) {
        final Session session = this.factory.openSession();
        Transaction trx = session.beginTransaction();
        try{
            final Query query = session.createQuery("from Task as task  where task.name = :name");
            query.setString("name",name);
            return (Task) query.list();
        }finally {
            trx.commit();
            session.close();
        }
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
        this.factory.close();
    }

}
