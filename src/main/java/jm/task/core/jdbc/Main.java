package jm.task.core.jdbc;
import static jm.task.core.jdbc.util.Util.HibernateUtil.getSessionFactory;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
       UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        User one = new User("Tomas", "Shelbi",  (byte)44);
        User two = new User("Richard", "Portman",  (byte)37);
        User three = new User("Leonardo", "Dicaprio",  (byte)49);
        User four = new User("Eva", "Green",  (byte)28);
        userDaoJDBC.saveUser(one.getName(), one.getLastName(), one.getAge());
        userDaoJDBC.saveUser(two.getName(), two.getLastName(), two.getAge());
        userDaoJDBC.saveUser(three.getName(), three.getLastName(), three.getAge());
        userDaoJDBC.saveUser(four.getName(), four.getLastName(), four.getAge());
        System.out.println(userDaoJDBC.getAllUsers());
        userDaoJDBC.removeUserById(3);
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();




        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        try (Session session = Util.HibernateUtil.getSessionFactory().openSession()) {
//            String sql = "select version()";
//
//            String result = (String) session.createNativeQuery(sql).getSingleResult();
//            System.out.println("My SQL version: " + result);
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }

    }
}
