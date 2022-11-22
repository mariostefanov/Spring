package org.example;

import org.example.entities.User;
import org.example.orm.EntityManager;
import org.example.orm.MyConnector;

import java.sql.Connection;
import java.sql.SQLException;

import static org.example.orm.MyConnector.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException {

        MyConnector.createConnection("root","150800","custom-orm");

        Connection connection = getConnection();

        EntityManager<User> userEntityManager = new EntityManager<>(connection);
    }
}