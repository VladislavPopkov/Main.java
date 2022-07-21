package jm.task.core.jdbc.dao;

import static jm.task.core.jdbc.util.Util.HibernateUtil.SessionFactory;
import static jm.task.core.jdbc.util.Util.HibernateUtil.getSessionFactory;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    //Session session = Util.HibernateUtil.getSession();
    Session session = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS `mydbtest`.`user` (\n" +
                "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                "`name` VARCHAR(45) NOT NULL,\n" +
                "`lastname` VARCHAR(45) NOT NULL,\n" +
                "`age` INT NOT NULL,\n" +
                "PRIMARY KEY (`id`))";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public void dropUsersTable() {
        session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS user";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        Query query = session.createQuery(criteriaQuery);
        List<User> UserList = query.getResultList();
        session.getTransaction().commit();
        return UserList;
    }

    @Override
    public void cleanUsersTable() {
        session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM User i ").executeUpdate();
        session.getTransaction().commit();
    }
}
