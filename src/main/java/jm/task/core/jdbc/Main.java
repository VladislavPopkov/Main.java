package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        User one = new User("Tomas", "Shelbi",  (byte)44);
        User two = new User("Richard", "Portman",  (byte)37);
        User three = new User("Leonardo", "Dicaprio",  (byte)49);
        User four = new User("Eva", "Green",  (byte)28);
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser(one.getName(), one.getLastName(), one.getAge());
        userDaoJDBC.saveUser(two.getName(), two.getLastName(), two.getAge());
        userDaoJDBC.saveUser(three.getName(), three.getLastName(), three.getAge());
        userDaoJDBC.saveUser(four.getName(), four.getLastName(), four.getAge());
        System.out.println(userDaoJDBC.getAllUsers());
       userDaoJDBC.cleanUsersTable();
       userDaoJDBC.dropUsersTable();
    }
}
