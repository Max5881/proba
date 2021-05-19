package ru.sapteh.Service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.DAO.DAO;
import ru.sapteh.Model.Product;

import java.util.List;

public class ProductService implements DAO<Product,Integer> {
    private final SessionFactory factory;

    public ProductService(SessionFactory factory) {
        this.factory = factory;
    }

    public void create(Product product) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }

    }

    public void delete(Product product) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public void update(Product product) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        }
    }

    public Product read(Integer integer) {
        try(Session session = factory.openSession()){
            Product result = session.get(Product.class,integer);
            return result;
        }
    }

    public List<Product> readByAll() {
        try(Session session = factory.openSession()){
            Query<Product> result = session.createQuery("FROM Product");
            return result.list();
        }
    }
}
