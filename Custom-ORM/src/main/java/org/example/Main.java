package org.example;

import org.example.entities.User;
import org.example.orm.EntityManager;
import org.example.orm.MyConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.example.orm.MyConnector.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {

        MyConnector.createConnection("root","150800","custom-orm");

        Connection connection = getConnection();

        EntityManager<User> userEntityManager = new EntityManager<>(connection);

//        User user = new User("mario", 22, LocalDate.now());
//        User user2 = new User("kiril", 15, LocalDate.now());
//       userEntityManager.persist(user);
//        userEntityManager.persist(user2);
        userEntityManager.doCreate(User.class);
        userEntityManager.doAlter(User.class);


    }
}