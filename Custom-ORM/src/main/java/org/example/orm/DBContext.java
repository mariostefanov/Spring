package org.example.orm;

import java.sql.SQLException;

public interface DBContext<E> {

    boolean persist(E entity) throws IllegalAccessException, SQLException;

    Iterable<E> find(Class<E> clazz);

    Iterable<E> find(Class<E> clazz, String where);

    E findFirst(Iterable<E> table);

    E findFirst(Iterable<E> table, String where);
}
