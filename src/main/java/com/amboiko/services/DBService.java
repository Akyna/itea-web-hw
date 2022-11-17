package com.amboiko.services;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBService {
    private final String dbHost;
    private final String user;
    private final String password;
    private static final String GET_USER = "SELECT email, password, user_name FROM user WHERE email= ? AND password= ?";
    private static final String INSERT_USER = "INSERT INTO user (email, password, user_name) VALUES(?, ?, ?)";
    Connection connection = null;
    Logger logger;

    public DBService() {
        this("jdbc:mysql://localhost:8889/hw_06_web", "root", "root");
    }

    public DBService(String dbHost, String user, String password) {
        this.dbHost = dbHost;
        this.user = user;
        this.password = password;
        logger = Logger.getLogger(DBService.class.getName());
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbHost, user, password);
    }

    public String getUserName(String email, String password) {
        String userName = null;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, Encoding.md5EncryptionWithSalt(password));

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userName = resultSet.getString("user_name");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.log(Level.WARNING, ex.getMessage());

        }
        return userName;
    }

    public boolean storeUser(String email, String password, String userName) {
        boolean result = false;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, Encoding.md5EncryptionWithSalt(password));
            preparedStatement.setString(3, userName);

            preparedStatement.executeUpdate();
            result = true;
            preparedStatement.close();
        } catch (SQLException ex) {
            logger.log(Level.WARNING, ex.getMessage());
        }

        return result;
    }


}
