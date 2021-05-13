package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }


    @Override

    public void createUsersTable() {

        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE if not exists users (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(25), lastName VARCHAR(40), age SMALLINT NOT NULL, PRIMARY KEY (id))").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("something wrong with table creation");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Problem with deletion");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Problem adding user " + name);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM users WHERE id =" + id).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Problem removing user with id =" + id);
        }
    }


    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> arr = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            arr = loadAllData(User.class, session);

            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Problem getting list of all users");
        }
        return arr;
    }

    //https://stackoverflow.com/questions/43037814/how-to-get-all-data-in-the-table-with-hibernate
    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }


    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM users where id is not null").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Problem with deletion");
        }
    }
}
