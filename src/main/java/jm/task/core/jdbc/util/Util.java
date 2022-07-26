package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.mapping.MetadataSource;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static jm.task.core.jdbc.util.Util.HibernateUtil.getSessionFactory;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Util util = new Util();
        util.getMySQLConnection();
    }

    public Connection getMySQLConnection() {
        Connection connection = null;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
        } catch (SQLException e) {
            System.out.println("Соединение с БД не установлено");
        }
        return connection;
    }

    public class HibernateUtil {
        public static final SessionFactory SessionFactory;
        static {
            try {
                Properties prop = new Properties();
                prop.setProperty("hibernate.connection.driver", "com.mysql.cj.jdbc.Driver");
                prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydbtest");
                prop.setProperty("hibernate.connection.username", "root");
                prop.setProperty("hibernate.connection.password", "root");
                prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
                prop.setProperty("hibernate.show_sql", "true");
                prop.setProperty("hibernate.current_session_context_class", "thread");

                SessionFactory = new org.hibernate.cfg.Configuration()
                        .addProperties(prop)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory()
                ;
            }
            catch (Exception ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }
        public static SessionFactory getSessionFactory() throws HibernateException {
            return SessionFactory;
        }
    }

}
