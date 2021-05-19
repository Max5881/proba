package ru.sapteh.DAO;

import java.util.List;

public interface DAO <Entity,Integer> {
    void create(Entity entity);
    void delete(Entity entity);
    void update(Entity entity);
    Entity read(Integer integer);
    List<Entity> readByAll();
}

