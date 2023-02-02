package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main  implements UserService {
    public static void main(String[] args) throws SQLException , NullPointerException {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 20);
        System.out.println("User с именем – Name1 добавлен в базу данных" );
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        System.out.println("User с именем – Name2 добавлен в базу данных" );
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        System.out.println("User с именем – Name3 добавлен в базу данных" );
        userDao.saveUser("Name4", "LastName4", (byte) 38);
        System.out.println("User с именем – Name4 добавлен в базу данных" );

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }



    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() { // удаление табл

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {

    return null;
    }

    @Override
    public void cleanUsersTable() { // очистка таблицы

    }
}
