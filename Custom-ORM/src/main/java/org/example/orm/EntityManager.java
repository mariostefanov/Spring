package org.example.orm;

import org.example.annotations.Column;
import org.example.annotations.Entity;
import org.example.annotations.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<E> implements DBContext<E> {

    private final Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    public void doCreate(Class<E> entityClass) throws SQLException {
        String tableName = getTableName(entityClass);
        String fieldsWithTypes = getSQLFieldsWithTypes(entityClass);

        String createQuery = String.format("CREATE TABLE %s (id INT PRIMARY_KEY AUTO_INCREMENT, %s)", tableName, fieldsWithTypes);

        PreparedStatement statement = connection.prepareStatement(createQuery);

        statement.execute();
    }

    private String getSQLFieldsWithTypes(Class<E> entityClass) {

        return Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(field -> {
                    String fieldName = field.getAnnotationsByType(Column.class)[0].name();
                    Class<?> type = field.getType();

                    String sqlType = "";

                    if (type == Integer.class || type == int.class) {
                        sqlType = "INT";
                    } else if (type == String.class) {
                        sqlType = "VARCHAR(200)";
                    } else if (type == LocalDate.class) {
                        sqlType = "DATE";
                    }

                    return fieldName + " " + sqlType;
                }).collect(Collectors.joining(", "));


    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field primaryKey = getId(entity.getClass());
        primaryKey.setAccessible(true);
        Object idValue = primaryKey.get(entity);

        if (idValue == null || (long) idValue <= 0) {
            return doInsert(entity, primaryKey);
        }

        //return doUpdate();

        return false;
    }

    private boolean doUpdate(E entity) throws IllegalAccessException {
        String tableName = getTableName(entity.getClass());
        String tablesFields = getColumnsWithoutId(entity.getClass());
        String columnsValues = getColumnsValuesWithoutId(entity);

        return false;
    }

    private boolean doInsert(E entity, Field idColumn) throws IllegalAccessException, SQLException {


        String tableName = getTableName(entity.getClass());
        String tableFields = getColumnsWithoutId(entity.getClass()); // username, age, registration_date
        String tableValues = getColumnsValuesWithoutId(entity);

        String insertQuery = String.format("INSERT INTO %s (%s) VALUES (%s)",
                tableName, tableFields, tableValues);

        return connection.prepareStatement(insertQuery).execute();
    }

    private String getColumnsWithoutId(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotationsByType(Column.class))
                .map(a -> a[0].name())
                .collect(Collectors.joining(","));
    }

    private String getColumnsValuesWithoutId(E entity) throws IllegalAccessException {
        Class<?> aClass = entity.getClass();
        List<Field> fields = Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

        List<String> values = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = field.get(entity);

            if (o instanceof String || o instanceof LocalDate) {
                values.add("'" + o + "'");
            } else {
                values.add(o.toString());
            }
        }

        return String.join(",", values);
    }

    private String getTableName(Class<?> aClass) {
        Entity[] annotationsByType = aClass.getAnnotationsByType(Entity.class);

        if (annotationsByType.length == 0) {
            throw new UnsupportedOperationException("Class must be Entity");
        }

        return annotationsByType[0].name();
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

    private Field getId(Class<?> clazz) {

        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() ->
                        new UnsupportedOperationException("Entity missing an id column"));
    }
}
