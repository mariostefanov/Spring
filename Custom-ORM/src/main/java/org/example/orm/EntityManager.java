package org.example.orm;

import java.sql.Connection;

public class EntityManager<E> implements DBContext<E>{

    private final Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) {
        return false;
    }

    @Override
    public Iterable<E> find(Class<E> clazz) {
        return null;
    }

    @Override
    public Iterable<E> find(Class<E> clazz, String where) {
        return null;
    }

    @Override
    public E findFirst(Iterable<E> table) {
        return null;
    }

    @Override
    public E findFirst(Iterable<E> table, String where) {
        return null;
    }
}
