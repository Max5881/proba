package ru.sapteh.Service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.DAO.DAO;
import ru.sapteh.Model.Category;


import java.util.List;

public class CategoryService implements DAO<Category,Integer> {
    private final SessionFactory factory;

    public CategoryService(SessionFactory factory) {
        this.factory = factory;
    }

    public void create(Category category) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
        }

    }

    public void delete(Category category) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(category);
            session.getTransaction().commit();
        }
    }

    public void update(Category category) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(category);
            session.getTransaction().commit();
        }
    }

    public Category read(Integer integer) {
        try(Session session = factory.openSession()){
            Category result = session.get(Category.class,integer);
            return result;
        }
    }

    public List<Category> readByAll() {
        try(Session session = factory.openSession()){
            Query<Category> result = session.createQuery("FROM Category");
            return result.list();
        }
    }
}
