package ru.sapteh.Service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.DAO.DAO;
import ru.sapteh.Model.Warehouse;

import java.util.List;

public class WarehouseService implements DAO<Warehouse,Integer> {
    private final SessionFactory factory;

    public WarehouseService(SessionFactory factory) {
        this.factory = factory;
    }

    public void create(Warehouse warehouse) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(warehouse);
            session.getTransaction().commit();
        }

    }

    public void delete(Warehouse warehouse) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(warehouse);
            session.getTransaction().commit();
        }
    }

    public void update(Warehouse warehouse) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(warehouse);
            session.getTransaction().commit();
        }
    }

    public Warehouse read(Integer integer) {
        try(Session session = factory.openSession()){
            Warehouse result = session.get(Warehouse.class,integer);
            return result;
        }
    }

    public List<Warehouse> readByAll() {
        try(Session session = factory.openSession()){
            Query<Warehouse> result = session.createQuery("FROM Warehouse");
            return result.list();
        }
    }
}
