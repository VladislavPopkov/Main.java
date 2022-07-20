package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    Util util = new Util();
    public UserDaoJDBCImpl() {
        this.connection = util.getMySQLConnection();
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `mydbtest`.`user` (\n" +
                                  "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                                  "`name` VARCHAR(45) NOT NULL,\n" +
                                  "`lastname` VARCHAR(45) NOT NULL,\n" +
                                  "`age` INT NOT NULL,\n" +
                                  "PRIMARY KEY (`id`));");
            System.out.println("table create");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("drop table if exists user");
                System.out.println("table drop");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name, lastName, age) values (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println(name + " добавлен(а) в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
//        String SQL = "delete from user where id = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USER WHERE ID = (?)")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("user " + id + " delete");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) { //statment отправляет запросы в БД
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user"); //ResultSet результирующий запрос предоставляющий построчный набор данных
                                                                                            //executeQuery - метод statment отправляющий SQL выражение в БД("SELECT * FROM user")
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM USER");
            System.out.println("users delete");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
