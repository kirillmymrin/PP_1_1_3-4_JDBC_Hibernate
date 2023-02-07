package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    private static final SessionFactory factory = getSessionFactory();


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() throws MappingException {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery("CREATE TABLE kata" +
                    "(ID INTEGER NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(255), " +
                    "last_name VARCHAR(255)," +
                    "age INTEGER, " +
                    "PRIMARY KEY (id))").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery("DROP TABLE kata").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws ClassCastException {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        } catch (ClassCastException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
    try (Session session = factory.openSession()){
        Query<User>query = session.createQuery("FROM User", User.class);
        return query.list();
    }
    }


    @Override
    public void cleanUsersTable() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery("TRUNCATE TABLE kata").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
