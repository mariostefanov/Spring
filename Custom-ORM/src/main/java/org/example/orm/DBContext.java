package org.example.orm;

public interface DBContext<E> {

    boolean persist(E entity);

    Iterable<E> find(Class<E> clazz);

    Iterable<E> find(Class<E> clazz, String where);

    E findFirst(Iterable<E> table);

    E findFirst(Iterable<E> table, String where);
}
